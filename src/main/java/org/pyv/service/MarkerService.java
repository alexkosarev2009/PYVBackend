package org.pyv.service;

import org.pyv.dto.MarkerDTO;
import java.util.List;

public interface MarkerService {
    List<MarkerDTO> getAllMarkers();
    MarkerDTO getMarkerById(Long id);
    MarkerDTO createMarker(MarkerDTO dto);
    MarkerDTO updateMarker(Long id, MarkerDTO dto);
    void deleteMarker(Long id);
}
