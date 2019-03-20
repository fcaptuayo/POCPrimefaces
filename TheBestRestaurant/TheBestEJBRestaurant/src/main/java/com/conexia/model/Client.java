package com.conexia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "client")
@NamedQueries({@NamedQuery(name = Client.FIND_ALL,
        query = "SELECT e FROM Client e")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Client.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;


    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "observation")
    @Size(max = 500)
    private String observation;

    @OneToMany(
            mappedBy = "client"
    )
    private List<Bill> bills;

    @Transient
    private Double price;

    public Client(String name, String lastName, Double price) {
        this.name = name;
        this.lastName = lastName;
        this.price = price != null ? price : 0D;
    }
}
