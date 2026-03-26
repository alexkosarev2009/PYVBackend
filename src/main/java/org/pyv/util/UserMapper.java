package org.pyv.util;

import lombok.experimental.UtilityClass;
import org.pyv.dto.UserDTO;
import org.pyv.entity.User;

@UtilityClass
public class UserMapper {
    public UserDTO userToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setBio(user.getBio());
        userDTO.setAvatarUrl(user.getAvatarUrl());
        return userDTO;
    }
}
