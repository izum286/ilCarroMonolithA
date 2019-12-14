package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.UserDTO;
import com.telran.ilcarro.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepo userRepo;

    PasswordEncoder encoder;

    @Override
    public void registration(String email, String password) {
        if (userRepo.existsById(email)) {
            throw new RuntimeException("User Already Exist");
        }
        UserDTO entity = new UserDTO();
        entity.setEmail(email);
        entity.setPassword(encoder.encode(password));
        //TODO set roles for user!!!!!
        userRepo.addUser(entity);
    }
}
