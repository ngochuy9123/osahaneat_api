package com.springboot.osahaneat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date create_date;

    @OneToMany(mappedBy = "category")
    private Set<Food> foodSet;

    @OneToMany(mappedBy = "category")
    private Set<MenuRestaurant> menuRestaurantSet;
}
