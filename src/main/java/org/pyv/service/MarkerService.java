package org.pyv.service;

import org.pyv.dto.CreateMarkerDTO;
import org.pyv.dto.MarkerDTO;
import org.pyv.entity.User;

import java.util.List;

public interface MarkerService {
    List<MarkerDTO> getAllMarkers();
    List<MarkerDTO> getAllMarkersByAuthorId(Long authorId);
    MarkerDTO getMarkerById(Long id);
    MarkerDTO createMarker(CreateMarkerDTO dto, User user);
    MarkerDTO updateMarker(Long id, MarkerDTO dto);
    void deleteMarker(Long id);
}
