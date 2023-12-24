package com.example.auth.services;


import com.example.auth.dto.UserDTO;
import com.example.auth.entities.User;
import com.example.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
public class Service implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User signup(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setSexe(userDTO.getSexe());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }

    @Override
    public boolean login(String email , String password) {
        try{
            if(userRepository.findByEmailAndPassword(email, password).get(0)!=null) {
                return true;
            }
        }catch(Exception e){
            throw new RuntimeException("user not found");
        }
        return false;
    }
}
