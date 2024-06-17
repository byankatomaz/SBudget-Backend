package tech.SBudget.SBudget.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="products")
@Entity(name="Product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name_supplier;
    private String name;
    private float price;
    private int quantity;
    private String category;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Budget> budgetList;

}
