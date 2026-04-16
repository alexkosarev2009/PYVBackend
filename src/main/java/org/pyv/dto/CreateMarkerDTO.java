package org.pyv.dto;
import lombok.Data;

@Data
public class CreateMarkerDTO {
    private long id;
    private String title;
    private double lat;
    private double lng;
    private String imageUrl;
    private String audioUrl;
    private long authorId;
}
