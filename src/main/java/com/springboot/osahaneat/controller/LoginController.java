package com.springboot.osahaneat.controller;

import com.springboot.osahaneat.dto.UserDto;
import com.springboot.osahaneat.entity.User;
import com.springboot.osahaneat.payload.ResponseData;
import com.springboot.osahaneat.payload.request.SignUpRequest;
import com.springboot.osahaneat.repository.UserRepository;
import com.springboot.osahaneat.service.LoginService;
import com.springboot.osahaneat.service.UserService;
import com.springboot.osahaneat.utils.JwtUtilsHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam String username,@RequestParam String password){
        ResponseData responseData = new ResponseData();



        if (loginService.checkLogin(username,password)){
            String token = jwtUtilsHelper.generateToken(username);
            responseData.setData(token);

        }
        else{
            responseData.setData("");
            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginService.addUser(signUpRequest));

        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }
}
