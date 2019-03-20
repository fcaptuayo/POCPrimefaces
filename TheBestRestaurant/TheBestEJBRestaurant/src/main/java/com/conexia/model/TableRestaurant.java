package com.conexia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "table_restaurant")
@NamedQueries({@NamedQuery(name = TableRestaurant.FIND_ALL,
        query = "SELECT e FROM TableRestaurant e")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableRestaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "TableRestaurant.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "capacity")
    @NotNull
    private Integer capacity;


    @Column(name = "location")
    @NotNull
    private String location;
}
