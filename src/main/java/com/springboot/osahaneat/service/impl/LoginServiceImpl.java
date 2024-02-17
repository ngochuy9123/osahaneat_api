package com.springboot.osahaneat.service.impl;

import com.springboot.osahaneat.entity.Roles;
import com.springboot.osahaneat.entity.User;
import com.springboot.osahaneat.payload.request.SignUpRequest;
import com.springboot.osahaneat.repository.UserRepository;
import com.springboot.osahaneat.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Boolean checkLogin(String username, String password) {
        User user = userRepository.findByUsername(username);

        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public Boolean addUser(SignUpRequest signUpRequest) {
        Roles role = new Roles();
        role.setId(signUpRequest.getRoleId());

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword());
        user.setEmail(signUpRequest.getEmail());
        user.setRoles(role);
        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
