package org.pyv.service.impl;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.UserDTO;
import org.pyv.repository.UserRepository;
import org.pyv.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        return null;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
