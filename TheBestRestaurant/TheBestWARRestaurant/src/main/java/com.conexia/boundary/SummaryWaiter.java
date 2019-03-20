package com.conexia.boundary;


import com.conexia.control.BillManager;
import com.conexia.model.Waiter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryWaiter {

    @EJB
    private BillManager manager;

    private List<Waiter> Waiters;

    @PostConstruct
    public void init() {
        this.Waiters = manager.summarizerWaiter();
    }
}
