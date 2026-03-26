package org.pyv.dto;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String username;
    private String bio;
    private String avatarUrl;
}
