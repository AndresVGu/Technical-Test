package com.shopproject.technicalTest.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String status;
    private Double total;
    //Create an object to represent the class Branch
    @ManyToOne
    private Branch branch;

    //add bidirectionality for JPA. JPA can create an intermediate table.
    //also needs to add the attribute for mapping correctly (name of the class, in this case is "sale")
    //with this relation we don't need to add a repository
    //EAGER force to bring always details
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL,
               orphanRemoval = true, fetch = FetchType.EAGER )
    private List<DetailSale> detail = new ArrayList<>();
}
