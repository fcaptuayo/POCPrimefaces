package com.conexia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill")
@NamedQueries({
        @NamedQuery(name = Bill.FIND_ALL,
        query = "SELECT e FROM Bill e")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Bill.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "creation_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @NotNull
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "table_restaurant_id")
    @NotNull
    private TableRestaurant tableRestaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "waiter_id")
    @NotNull
    private Waiter waiter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client;

    @OneToMany(
            mappedBy = "bill",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Product> products;

}
