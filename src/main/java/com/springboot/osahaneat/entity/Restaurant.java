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
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String subtitle;
    private String description;
    private String image;
    private boolean free_shipping;
    private String address;
    private Date open_date;


    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RatingRestaurant> ratingRestaurantSet;

    @OneToMany(mappedBy = "restaurant")
    private Set<Orders> ordersSet;

    @OneToMany(mappedBy = "restaurant")
    private Set<MenuRestaurant> menuRestaurantSet ;

    @OneToMany(mappedBy = "restaurant")
    private Set<Promo> promoSet;

}
