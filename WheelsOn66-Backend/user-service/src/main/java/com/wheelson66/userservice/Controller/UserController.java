package com.wheelson66.userservice.Controller;

import com.wheelson66.userservice.Dto.LoginRequest;
import com.wheelson66.userservice.Dto.RegisterRequest;
import com.wheelson66.userservice.Dto.UserResponse;
import com.wheelson66.userservice.Entity.User;
import com.wheelson66.userservice.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse CreateUser(@RequestBody RegisterRequest request){
        User savedUser = userService.registerUser(request.getEmail(), request.getRawPassword(), request.getPseudo());
        return new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getPseudo());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return userService.loginUser(request.getEmail(), request.getRawPassword());
    }
}