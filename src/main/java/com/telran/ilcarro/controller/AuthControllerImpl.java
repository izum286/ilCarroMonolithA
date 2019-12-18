package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.user.AuthDTO;
import com.telran.ilcarro.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController{
    @Autowired
    AuthService authService;

    @PostMapping("registration")
    @Override
    public void registration(@RequestBody AuthDTO authDTO) {
        authService.registration(authDTO.getEmail(), authDTO.getPassword());
    }
}
