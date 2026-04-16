package org.pyv.service;

import org.pyv.dto.CreateMarkerDTO;
import org.pyv.dto.MarkerDTO;
import java.util.List;

public interface MarkerService {
    List<MarkerDTO> getAllMarkers();
    MarkerDTO getMarkerById(Long id);
    MarkerDTO createMarker(CreateMarkerDTO dto);
    MarkerDTO updateMarker(Long id, MarkerDTO dto);
    void deleteMarker(Long id);
}
