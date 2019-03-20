package com.conexia.control;

import com.conexia.model.Bill;
import com.conexia.model.Client;
import com.conexia.model.Product;
import com.conexia.model.Waiter;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


@Stateless
@Transactional
public class BillManager {

    @PersistenceContext()
    private EntityManager em;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public List<Bill> all() {
        return (List<Bill>) this.em.createNamedQuery(Bill.FIND_ALL).getResultList();
    }


    public void create(@NotNull Bill bill, @NotNull @NotEmpty List<Product> products) {
        this.em.persist(this.buildBill(bill, products));
    }

    private Bill buildBill(Bill bill, List<Product> products) {
        bill.setCreationDate(new Date());
        bill.setProducts(products);
        return bill;
    }

    public List<Client> summarizerClient() {
        Double limitVIP = 100000D;
        return processSummarizerClient(limitVIP);
    }

    public List<Waiter> summarizerWaiter() {
        return processSummarizerWaiter();
    }

    private List<Waiter> processSummarizerWaiter() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Waiter> cq = cb.createQuery(Waiter.class);

        Root<Waiter> waiterRoot = cq.from(Waiter.class);
        Join<Waiter, Bill> bills = waiterRoot.join("bills", JoinType.LEFT);
        Join<Bill, Product> products = bills.join("products", JoinType.LEFT);

        cq.select(
                cb.construct(
                        Waiter.class,
                        waiterRoot.get("name"),
                        waiterRoot.get("lastName"),
                        cb.sum(products.get("price")
                        )
                )
        ).groupBy(waiterRoot.get("name"), waiterRoot.get("lastName"));

        TypedQuery<Waiter> query = em.createQuery(cq);
        return query.getResultList();

    }


    private List<Client> processSummarizerClient(Double limitVIP) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);

        Root<Client> clientRoot = cq.from(Client.class);
        Join<Client, Bill> bills = clientRoot.join("bills", JoinType.LEFT);
        Join<Bill, Product> products = bills.join("products", JoinType.LEFT);

        cq.select(
                cb.construct(
                        Client.class,
                        clientRoot.get("name"),
                        clientRoot.get("lastName"),
                        cb.sum(products.get("price"))
                )
        ).having(cb.gt(cb.sum(products.get("price")),limitVIP))
                .groupBy(clientRoot.get("name"), clientRoot.get("lastName"));

        TypedQuery<Client> query = em.createQuery(cq);
        return query.getResultList();
    }
}
