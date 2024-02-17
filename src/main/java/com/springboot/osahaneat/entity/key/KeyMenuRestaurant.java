package com.springboot.osahaneat.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class KeyMenuRestaurant implements Serializable {
    @Column(name = "category_id")
    private int categoryId;
    @Column(name = "restaurant_id")
    private int restaurantId;
}
