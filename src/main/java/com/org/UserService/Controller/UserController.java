package com.org.UserService.Controller;

import com.org.UserService.DTO.LoginRequest;
import com.org.UserService.DTO.ResponseDTO;
import com.org.UserService.DTO.UserDTO;
import com.org.UserService.Entity.User;
import com.org.UserService.Repository.UserRepository;
import com.org.UserService.Security.JWT.JwtUtils;
import com.org.UserService.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/public/get")
    public String get() {
        return "Hello World";
    }

    @PostMapping("/auth/register")
    public ResponseDTO register(@RequestBody UserDTO userDto) {
        return userService.registerUser(userDto);
    }

    @PostMapping("/auth/login")
    public ResponseDTO login(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }

    @GetMapping("/admin/get")
    public String adminGet() {
        return "Hello Admin";
    }

    @GetMapping("/user/get")
    public String userGet() {
        return "Hello User";
    }

}
