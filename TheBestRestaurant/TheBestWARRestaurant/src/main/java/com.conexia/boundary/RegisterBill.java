package com.conexia.boundary;


import com.conexia.control.*;
import com.conexia.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBill {

    @EJB
    private WaiterManager waiterManager;

    @EJB
    private TableManager tableManager;

    @EJB
    private ClientManager clientManager;

    @EJB
    private BillManager billManager;

    private List<Bill> bills;
    private List<Waiter> waiters;
    private List<Client> clients;
    private List<TableRestaurant> tables;
    private List<Product> products;

    private Bill bill = new Bill();
    private Waiter waiter = new Waiter();
    private TableRestaurant tableRestaurant = new TableRestaurant();
    private Client client = new Client();
    private Product product = new Product();

    @PostConstruct
    public void init() {
        this.bills = billManager.all();
        this.waiters = waiterManager.all();
        this.clients = clientManager.all();
        this.tables = tableManager.all();
        this.products = new ArrayList<>();
    }

    public void create() {
        billManager.create(bill, products);
        this.bills = billManager.all();
        this.bill = new Bill();
        this.products = new ArrayList<>();
        this.putGrowl("Se registro exitosamente la factura.");
    }

    public void createWaiter() {
        waiterManager.create(waiter);
        this.waiters = waiterManager.all();
        this.waiter = new Waiter();
        this.putGrowl("Se registro exitosamente el mesero.");
    }

    public void createClient() {
        clientManager.create(client);
        this.clients = clientManager.all();
        this.putGrowl("Se registro exitosamente el cliente.");
    }

    public void createTable() {
        tableManager.create(tableRestaurant);
        this.tables = tableManager.all();
        this.tableRestaurant = new TableRestaurant();

        this.putGrowl("Se registro exitosamente la mesa.");
    }

    public void addProduct() {
        this.product.setBill(bill);
        this.products.add(product);
        this.product = new Product();
        this.putGrowl("Se Agrego el producto exitosament a la factura.");
    }

    private void putGrowl(String response) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, response, "Success update"));
    }
}
