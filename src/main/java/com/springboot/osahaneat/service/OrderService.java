package com.springboot.osahaneat.service;

import com.springboot.osahaneat.payload.request.OrderRequest;

public interface OrderService {
    boolean createOrder(OrderRequest orderRequest);
}
