package com.springboot.osahaneat.entity;

import com.springboot.osahaneat.entity.key.KeyOrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "order_item")
public class OrderItem {
    @EmbeddedId
    KeyOrderItem keyOrderItem;

    @ManyToOne
    @JoinColumn(name = "orders_id",insertable = false,updatable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "food_id",insertable = false,updatable = false)
    private Food food;

    private Date create_date;
}
