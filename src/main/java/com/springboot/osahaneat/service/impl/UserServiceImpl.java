package com.springboot.osahaneat.service.impl;

import com.springboot.osahaneat.dto.UserDto;
import com.springboot.osahaneat.entity.Roles;
import com.springboot.osahaneat.entity.User;
import com.springboot.osahaneat.payload.request.SignUpRequest;
import com.springboot.osahaneat.repository.UserRepository;
import com.springboot.osahaneat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user:users) {
            UserDto userDto = new UserDto(
                    user.getId(), user.getUsername(), user.getPassword(),
                    user.getEmail(),user.getCreate_date());
            userDtos.add(userDto);
        }
        return userDtos;
    }


}
