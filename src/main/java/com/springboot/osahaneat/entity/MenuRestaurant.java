package com.springboot.osahaneat.entity;


import com.springboot.osahaneat.entity.key.KeyMenuRestaurant;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "menu_restaurant")
public class MenuRestaurant {
    @EmbeddedId
    KeyMenuRestaurant keys;

    private Date create_date;

    @ManyToOne
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "restaurant_id",insertable = false,updatable = false)
    private Restaurant restaurant;
}
