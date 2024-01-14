    package com.example.auth.services;


    import com.example.auth.dto.UserDTO;
    import com.example.auth.entities.Users;

    public interface AuthService {
        Users signup(UserDTO userDTO);
        boolean login(String email , String password);
    }