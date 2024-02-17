package com.springboot.osahaneat.repository;

import com.springboot.osahaneat.entity.OrderItem;
import com.springboot.osahaneat.entity.key.KeyOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, KeyOrderItem> {
}
