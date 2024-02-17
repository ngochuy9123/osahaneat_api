package com.springboot.osahaneat.controller;

import com.springboot.osahaneat.dto.UserDto;
import com.springboot.osahaneat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser(){
        List<UserDto> userDtos = userService.getAllUser();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
}
