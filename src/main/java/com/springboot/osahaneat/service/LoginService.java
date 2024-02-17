package com.springboot.osahaneat.service;

import com.springboot.osahaneat.payload.request.SignUpRequest;

public interface LoginService {
    Boolean checkLogin(String username,String password);
    Boolean addUser(SignUpRequest signUpRequest);
}
