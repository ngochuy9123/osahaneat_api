package com.springboot.osahaneat.controller;

import com.springboot.osahaneat.payload.ResponseData;
import com.springboot.osahaneat.payload.request.OrderRequest;
import com.springboot.osahaneat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    public ResponseEntity<?> getAllUser(@RequestBody OrderRequest orderRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(orderService.createOrder(orderRequest));
        return ResponseEntity.ok(responseData);
    }
}
