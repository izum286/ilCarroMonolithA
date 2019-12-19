package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.feedback.FeedbackDTO;
import com.telran.ilcarro.model.web.user.AuthDTO;
import com.telran.ilcarro.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
/**
 * AuthController implementation
 * @see AuthController
 *
 * @author Konkin Anton
 * @since 1.0
 */
@RestController
public class AuthControllerImpl implements AuthController{
    @Autowired
    AuthService authService;

    @ApiOperation(value = "Register new user", response = FeedbackDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 409, message = "User {email} already exist"),
    }
    )

    @PostMapping("registration")
    @Override
    public void registration(@RequestBody AuthDTO authDTO, @RequestHeader("Authorization") String token) {
        if(authService.registration(token)) {

        };
    }
}
