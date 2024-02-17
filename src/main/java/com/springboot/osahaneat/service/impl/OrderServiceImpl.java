package com.springboot.osahaneat.service.impl;

import com.springboot.osahaneat.entity.*;
import com.springboot.osahaneat.entity.key.KeyOrderItem;
import com.springboot.osahaneat.payload.request.OrderRequest;
import com.springboot.osahaneat.repository.OrderItemRepository;
import com.springboot.osahaneat.repository.OrdersRepository;
import com.springboot.osahaneat.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public boolean createOrder(OrderRequest orderRequest) {

        try{
            User user = new User();
            user.setId(orderRequest.getUserId());

            Restaurant restaurant = new Restaurant();
            restaurant.setId(orderRequest.getRestaurantId());

            Orders orders = new Orders();
            orders.setUser(user);
            orders.setRestaurant(restaurant);
            ordersRepository.save(orders);

            List<OrderItem> orderItems = new ArrayList<>();
            for(int idFood:orderRequest.getFoodIds()){
                Food food = new Food();
                food.setId(idFood);

                OrderItem orderItem = new OrderItem();
                KeyOrderItem keyOrderItem = new KeyOrderItem(orders.getId(),idFood);
                orderItem.setKeyOrderItem(keyOrderItem);

                orderItems.add(orderItem);
            }
            orderItemRepository.saveAll(orderItems);
            return true;
        }catch (Exception e){
            System.out.println("Error when create Orders: "+e.getMessage());
            return false;
        }


    }
}
