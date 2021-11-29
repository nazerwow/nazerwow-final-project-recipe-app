package com.ironhack.userservice.services;

import com.ironhack.userservice.dao.User;
import com.ironhack.userservice.dto.CreateUserDTO;
import com.ironhack.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    void deleteUser(Long id);

    User addUser(CreateUserDTO createUserDTO);

    User updateUser(Long id, UserDTO userDTO);

    User findByUsername(String username);
}
