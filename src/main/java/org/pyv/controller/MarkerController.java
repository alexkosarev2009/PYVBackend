package org.pyv.controller;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.CreateMarkerDTO;
import org.pyv.dto.MarkerDTO;
import org.pyv.entity.User;
import org.pyv.service.MarkerService;
import org.pyv.service.UserService;
import org.pyv.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/markers")
@RequiredArgsConstructor
public class MarkerController {
    private final MarkerService markerService;
    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<MarkerDTO>> getAllMarkers() {
        return ResponseEntity.ok(markerService.getAllMarkers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarkerDTO> getMarkerById(@PathVariable Long id) {
        return ResponseEntity.ok(markerService.getMarkerById(id));
    }

    @GetMapping("by-author-id")
    public ResponseEntity<List<MarkerDTO>> getAllMarkersByAuthorId(@RequestParam Long authorId,
                                                                   @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(markerService.getAllMarkersByAuthorId(authorId, user.getId()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MarkerDTO>> searchMarkersByTitle(@RequestParam String query,
                                                                @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(markerService.searchMarkersByTitle(user.getId(), query));
    }

    @PostMapping
    public ResponseEntity<MarkerDTO> createMarker(@RequestBody CreateMarkerDTO markerDTO,
                                                  @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(markerService.createMarker(markerDTO, userService.getCurrentUser(user)));
    }

    @GetMapping("/public")
    public ResponseEntity<List<MarkerDTO>> getPublicMarkers(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(markerService.getPublicMarkers(userService.getCurrentUser(user).getId()));
    }

    @GetMapping("/available")
    public ResponseEntity<List<MarkerDTO>> getAvailableMarkers(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(markerService.getAllAvailableMarkers(user.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarkerDTO> updateMarker(@PathVariable Long id, @RequestBody MarkerDTO markerDTO) {
        return ResponseEntity.ok(markerService.updateMarker(id, markerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarker(@PathVariable Long id,
                                             @AuthenticationPrincipal User user) {
        markerService.deleteMarker(id, user);
        return ResponseEntity.noContent().build();
    }
}
