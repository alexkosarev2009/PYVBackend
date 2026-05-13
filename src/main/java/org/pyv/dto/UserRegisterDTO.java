package org.pyv.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String name;
    private String username;
    private String email;
    private String password;
    private String bio = "Empty bio";
    private String avatarUrl = "https://storage.yandexcloud.net/pin-your-voice/images/default_avatar.jpg";
}
