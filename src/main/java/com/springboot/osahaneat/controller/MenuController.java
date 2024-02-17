package com.springboot.osahaneat.controller;

import com.springboot.osahaneat.payload.ResponseData;
import com.springboot.osahaneat.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    @PostMapping
    public ResponseEntity<?> createMenu(@RequestParam MultipartFile file,
                                        @RequestParam String title,
                                        @RequestParam boolean free_ship,
                                        @RequestParam String time_ship,
                                        @RequestParam double price,
                                        @RequestParam int cate_id){
        ResponseData responseData = new ResponseData();
        responseData.setData(menuService.createMenu(file,title,free_ship,time_ship,price,cate_id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
