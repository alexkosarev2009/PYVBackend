package org.pyv.service.impl;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.UserDTO;
import org.pyv.dto.UserRegisterDTO;
import org.pyv.entity.Authority;
import org.pyv.entity.User;
import org.pyv.exception.AuthorityNotFoundException;
import org.pyv.exception.UserNotFoundException;
import org.pyv.exception.UsernameAlreadyExistsException;
import org.pyv.repository.AuthorityRepository;
import org.pyv.repository.UserRepository;
import org.pyv.service.UserService;
import org.pyv.util.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

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

    public User getCurrentUser(User user) {
        User actualUser = userRepository.findById(user.getId())
                .orElseThrow();

        return actualUser;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::userToDTO)
                .orElseThrow(() -> new UserNotFoundException("No user with such username!"));
    }

    @Override
    public UserDTO createUser(UserRegisterDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        }
        Optional<Authority> roleUser = authorityRepository.findByAuthority("ROLE_USER");
        if (roleUser.isEmpty()) {
            throw new AuthorityNotFoundException("Authority not found!");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setBio(dto.getBio());
        user.setAvatarUrl(dto.getAvatarUrl());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setAuthorities(Set.of(roleUser.get()));
        return UserMapper.userToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        }
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
