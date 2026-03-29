package org.pyv.service;

import org.pyv.dto.UserDTO;
import org.pyv.dto.UserRegisterDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO getUserByUsername(String username);
    UserDTO createUser(UserRegisterDTO dto);
    UserDTO updateUser(Long id, UserDTO dto);
    void deleteUser(Long id);
}