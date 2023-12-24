    package com.example.auth.services;


    import com.example.auth.dto.UserDTO;
    import com.example.auth.entities.User;

    import java.util.List;

    public interface AuthService {
        User signup(UserDTO userDTO);
        boolean login(String email , String password);
    }