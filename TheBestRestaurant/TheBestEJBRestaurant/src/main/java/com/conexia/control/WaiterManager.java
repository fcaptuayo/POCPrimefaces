package com.conexia.control;

import com.conexia.model.Waiter;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.logging.Logger;


@Stateless
@Transactional
public class WaiterManager {

    @PersistenceContext()
    private EntityManager em;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public List<Waiter> all() {
        return (List<Waiter>) this.em.createNamedQuery(Waiter.FIND_ALL).getResultList();
    }

    public void create(@NotNull Waiter element) {
        this.em.persist(element);
    }
}