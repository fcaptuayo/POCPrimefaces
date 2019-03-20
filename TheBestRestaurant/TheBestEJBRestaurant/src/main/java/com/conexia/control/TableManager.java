package com.conexia.control;

import com.conexia.model.TableRestaurant;

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
public class TableManager {

    @PersistenceContext()
    private EntityManager em;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public List<TableRestaurant> all() {
        return (List<TableRestaurant>) this.em.createNamedQuery(TableRestaurant.FIND_ALL).getResultList();
    }

    public void create(@NotNull TableRestaurant element) {
        this.em.persist(element);
    }
}