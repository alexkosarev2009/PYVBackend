package org.pyv.util;

import lombok.experimental.UtilityClass;
import org.pyv.dto.MarkerDTO;
import org.pyv.entity.Marker;

@UtilityClass
public class MarkerMapper {
    public MarkerDTO markerToDTO(Marker marker) {
        MarkerDTO markerDTO = new MarkerDTO();
        markerDTO.setId(marker.getId());
        markerDTO.setTitle(marker.getTitle());
        markerDTO.setLat(marker.getLat());
        markerDTO.setLng(marker.getLng());
        markerDTO.setImageUrl(marker.getImageUrl());
        markerDTO.setAudioUrl(marker.getAudioUrl());
        markerDTO.setAuthorName(marker.getAuthor().getName());
        markerDTO.setAuthorUsername(marker.getAuthor().getUsername());
        markerDTO.setAuthorAvatarUrl(marker.getAuthor().getAvatarUrl());
        markerDTO.setCreatedAt(marker.getCreatedAt());
        return markerDTO;
    }
}
