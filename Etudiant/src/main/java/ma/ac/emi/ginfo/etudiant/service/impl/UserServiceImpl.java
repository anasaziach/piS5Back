package ma.ac.emi.ginfo.etudiant.service.impl;

import ma.ac.emi.ginfo.etudiant.dto.UserDTO;
import ma.ac.emi.ginfo.etudiant.entity.User;
import ma.ac.emi.ginfo.etudiant.repository.UserRepository;
import ma.ac.emi.ginfo.etudiant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        // Set other fields as needed
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(()->new RuntimeException("user not fround"));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO updatedUserDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("user not fround"));
        if (user != null) {
            user.setName(updatedUserDTO.getName());
            // Set other fields as needed
            User updatedUser = userRepository.save(user);
            return convertToDTO(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        // Set other fields as needed
        return userDTO;
    }
}