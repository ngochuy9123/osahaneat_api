package com.springboot.osahaneat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private Date create_date;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles roles;

    @OneToMany(mappedBy = "user")
    private Set<RatingFood> ratingFoodSet;

    @OneToMany(mappedBy = "user")
    private Set<RatingRestaurant> ratingRestaurantSet;

    @OneToMany(mappedBy = "user")
    private Set<Orders> ordersSet;

}
