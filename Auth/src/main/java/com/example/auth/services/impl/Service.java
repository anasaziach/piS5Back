package com.example.auth.services.impl;


import com.example.auth.dto.UserDTO;
import com.example.auth.entities.Users;
import com.example.auth.repositories.UserRepository;
import com.example.auth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
public class Service implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Users signup(UserDTO userDTO) {
        if(!userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            Users user = new Users();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setAge(userDTO.getAge());
            user.setSexe(userDTO.getSexe());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            return userRepository.save(user);
        }
        else{
            throw new RuntimeException("user already exist!");
        }
    }

    @Override
    public boolean login(String email , String password) {
        try{
            if(userRepository.findByEmailAndPassword(email, password).isPresent()) {
                return true;
            }
            else{
                throw new RuntimeException("user not found");
            }
        }catch(Exception e){
        }
        return false;
    }
}
