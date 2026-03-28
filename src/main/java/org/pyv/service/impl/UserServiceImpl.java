package org.pyv.service.impl;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.UserDTO;
import org.pyv.entity.User;
import org.pyv.exception.UserNotFoundException;
import org.pyv.exception.UsernameAlreadyExistsException;
import org.pyv.repository.UserRepository;
import org.pyv.service.UserService;
import org.pyv.util.UserMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::userToDTO)
                .toList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::userToDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::userToDTO)
                .orElseThrow(() -> new UserNotFoundException("No user with such username!"));
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setBio(dto.getBio());
        user.setAvatarUrl(dto.getAvatarUrl());
        return UserMapper.userToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setBio(dto.getBio());
        user.setAvatarUrl(dto.getAvatarUrl());
        return UserMapper.userToDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found!");
        }
        userRepository.deleteById(id);
    }
}
