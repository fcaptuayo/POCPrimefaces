package com.conexia.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "waiter")
@NamedQueries({@NamedQuery(name = Waiter.FIND_ALL,
        query = "SELECT e FROM Waiter e")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Waiter implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Waiter.findAll";

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

    @OneToMany(
            mappedBy = "waiter"
    )
    private List<Bill> bills;

    @Transient
    private Double price;

    public Waiter(String name, String lastName, Double price) {
        this.name = name;
        this.lastName = lastName;
        this.price = price != null ? price : 0D;
    }

}
