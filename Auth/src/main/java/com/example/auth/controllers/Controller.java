package com.example.auth.controllers;


import com.example.auth.dto.UserDTO;
import com.example.auth.dto.UserLoginDTO;
import com.example.auth.entities.Users;
import com.example.auth.services.impl.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class Controller {
    @Autowired
    Service service;
    @PostMapping("api/auth/login")
    public boolean login(@RequestBody UserLoginDTO user){
        return service.login(user.getEmail(),user.getPassword());
    }
    @PostMapping("api/auth/signup")
    public Users signup(@RequestBody UserDTO user){
        return service.signup(user);
    }

}
