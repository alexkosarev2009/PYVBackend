package org.pyv.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String username;
    private String bio;
    private String avatarUrl;
    private Instant createdAt;
}
