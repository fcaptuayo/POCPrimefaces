package com.conexia.boundary;


import com.conexia.control.BillManager;
import com.conexia.model.Client;
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
public class SummaryClient {

    @EJB
    private BillManager manager;

    private List<Client> clients;

    @PostConstruct
    public void init() {
        this.clients = manager.summarizerClient();
    }
}



