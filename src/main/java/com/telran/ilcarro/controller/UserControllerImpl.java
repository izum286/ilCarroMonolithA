package com.telran.ilcarro.controller;

import com.telran.ilcarro.controller.interfaces.UserController;
import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;
import com.telran.ilcarro.service.auth.AuthService;
import com.telran.ilcarro.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 *
 * UserController interface implementation
 *
 * @author Konkin Anton
 * @date 19.12.2019
 */
@CrossOrigin
@RestController
public class UserControllerImpl  implements UserController {
    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    //=============================================================================

    @ApiOperation(value = "Register new user", response = FullUserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 409, message = "User {email} already exist"),
    }
    )

    @PostMapping("registration")
    @Override
    public FullUserDTO registration(@RequestBody RegUserDTO user, @RequestHeader("Authorization") String token) {
        String userEmail = authService.registration(token);
        return userService.addUser(userEmail, user).orElseThrow();
    }

    //=============================================================================

    @ApiOperation(value = "Login", response = FullUserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized"),
    }
    )
    @PostMapping("user/login")
    @Override
    public FullUserDTO login(Principal principal) {
        String userEmail = principal.getName();

        return userService.getUser(userEmail).orElseThrow();
    }

    //=============================================================================

    @ApiOperation(value = "Update user and password", response = FullUserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized"),
    }
    )
    @PutMapping("user")
    @Override
    public FullUserDTO updateUser(@RequestBody UpdUserDTO updUser,
                                  @RequestHeader("X-New-Password") String newPassword,
                                  Principal principal
    ) {
        String userEmail = authService.updatePassword(principal.getName(), newPassword);
        return userService.updateUser(userEmail, updUser).orElseThrow();
    }

    //=============================================================================

    @ApiOperation(value = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized"),
    }
    )
    @DeleteMapping("user")
    @Override
    public void deleteUser(Principal principal) {
        String userEmail = principal.getName();
        userService.deleteUser(userEmail);
    }
}
