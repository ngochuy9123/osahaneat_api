package com.springboot.osahaneat.service;

import com.springboot.osahaneat.dto.UserDto;
import com.springboot.osahaneat.payload.request.SignUpRequest;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUser();

}
