package com.springboot.osahaneat.controller;

import com.springboot.osahaneat.payload.ResponseData;
import com.springboot.osahaneat.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<?> getHomeCategory(){
        ResponseData responseData =  new ResponseData();

        responseData.setData(categoryService.getCategoryHomePage());

        return ResponseEntity.ok(responseData);
    }
}
