package com.example.auth.controllers;


import com.example.auth.dto.UserDTO;
import com.example.auth.dto.UserLoginDTO;
import com.example.auth.entities.User;
import com.example.auth.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    Service service;
    @PostMapping("api/auth/login")
    public boolean login(@RequestBody UserLoginDTO user){
        return service.login(user.getEmail(),user.getPassword());
    }
    @PostMapping("api/auth/signup")
    public User signup(@RequestBody UserDTO user){
        return service.signup(user);
    }

}
