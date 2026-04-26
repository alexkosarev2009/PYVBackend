package org.pyv.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class MarkerDTO {
    private long id;
    private String title;
    private double lat;
    private double lng;
    private String imageUrl;
    private String audioUrl;
    private String authorName;
    private String authorUsername;
    private String authorAvatarUrl;
    private Instant createdAt;
}
