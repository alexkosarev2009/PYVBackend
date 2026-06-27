package org.pyv.dto;

import lombok.Data;

@Data
public class UpdateUserDTO {
    private String username;
    private String name;
    private String avatarUrl;
    private String bio;
}
