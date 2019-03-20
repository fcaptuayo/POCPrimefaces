package com.conexia.control;

import com.conexia.model.Client;

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
public class ClientManager {

    @PersistenceContext()
    private EntityManager em;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public List<Client> all() {
        return (List<Client>) this.em.createNamedQuery(Client.FIND_ALL).getResultList();
    }

    public void create(@NotNull Client element) {
        this.em.persist(element);
    }
}