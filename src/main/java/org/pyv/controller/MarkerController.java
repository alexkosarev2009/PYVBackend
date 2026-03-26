package org.pyv.controller;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.MarkerDTO;
import org.pyv.service.MarkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/marker")
@RequiredArgsConstructor
public class MarkerController {
    private final MarkerService markerService;

    @GetMapping
    public ResponseEntity<List<MarkerDTO>> getAllMarkers() {
        return ResponseEntity.ok(markerService.getAllMarkers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarkerDTO> getMarkerById(@PathVariable Long id) {
        return ResponseEntity.ok(markerService.getMarkerById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<MarkerDTO> createMarker(@RequestBody MarkerDTO markerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(markerService.createMarker(markerDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<MarkerDTO> updateMarker(@PathVariable Long id, @RequestBody MarkerDTO markerDTO) {
        return ResponseEntity.ok(markerService.updateMarker(id, markerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarker(@PathVariable Long id) {
        markerService.deleteMarker(id);
        return ResponseEntity.noContent().build();
    }
}
