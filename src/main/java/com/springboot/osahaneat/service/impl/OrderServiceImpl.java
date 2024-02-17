package com.springboot.osahaneat.service.impl;

import com.springboot.osahaneat.payload.request.OrderRequest;
import com.springboot.osahaneat.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public boolean createOrder(OrderRequest orderRequest) {
        return false;
    }
}
