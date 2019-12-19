package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.feedback.FeedbackDTO;
import com.telran.ilcarro.model.web.user.FullUserDTO;
import com.telran.ilcarro.model.web.user.RegUserDTO;
import com.telran.ilcarro.service.AuthService;
import com.telran.ilcarro.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * UserController interface implementation
 *
 * @author Konkin Anton
 * @date 19.12.2019
 */
@RestController
public class UserControllerImpl  implements UserController{
    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "Register new user", response = FeedbackDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 409, message = "User {email} already exist"),
    }
    )

    @PostMapping("registration")
    @Override
    public FullUserDTO registration(RegUserDTO user, String token) {
        //TODO RegUser -> FullUserDTO
        String userEmail = authService.registration(token);
        userService.addUser(userEmail, user);
        return null;
    }

    @Override
    public FullUserDTO login(String token) {
        return null;
    }

    @Override
    public FullUserDTO updateUser(FullUserDTO updUser, String token) {
        return null;
    }

    @Override
    public void deleteUser(String token) {

    }
}
