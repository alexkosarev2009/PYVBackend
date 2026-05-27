package org.pyv.service.impl;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.CreateMarkerDTO;
import org.pyv.dto.MarkerDTO;
import org.pyv.entity.Marker;
import org.pyv.entity.User;
import org.pyv.exception.MarkerNotFoundException;
import org.pyv.repository.MarkerRepository;
import org.pyv.service.MarkerService;
import org.pyv.util.MarkerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarkerServiceImpl implements MarkerService {
    private final MarkerRepository markerRepository;

    @Override
    public List<MarkerDTO> getAllMarkers() {
        return markerRepository.findAll().stream()
                .map(MarkerMapper::markerToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MarkerDTO> getPublicMarkers(Long authorId) {
        return markerRepository.findByAuthor_IdNotOrderByCreatedAtDesc(authorId).stream()
                .map(MarkerMapper::markerToDTO)
                .toList();
    }

    @Override
    public List<MarkerDTO> getAllAvailableMarkers(Long userId) {
        return markerRepository.findAvailableMarkers(userId)
                .stream().map(MarkerMapper::markerToDTO)
                .toList();
    }

    @Override
    public MarkerDTO getMarkerById(Long id) {
        return markerRepository.findById(id)
                .map(MarkerMapper::markerToDTO)
                .orElseThrow(() -> new MarkerNotFoundException("Marker not found!"));
    }

    @Override
    public List<MarkerDTO> getAllMarkersByAuthorId(Long authorId, Long userId) {
        return markerRepository.findAllVisibleAuthorMarkers(authorId, userId )
                .stream().map(MarkerMapper::markerToDTO)
                .toList();
    }

    @Override
    public MarkerDTO createMarker(CreateMarkerDTO dto, User user) {
        Marker marker = new Marker();
        marker.setTitle(dto.getTitle());
        marker.setLat(dto.getLat());
        marker.setLng(dto.getLng());
        marker.setImageUrl(dto.getImageUrl());
        marker.setAudioUrl(dto.getAudioUrl());
        marker.setAuthor(user);
        marker.setAmplitudes(dto.getAmplitudes());
        marker.setIcon(dto.getIcon());
        marker.setVisibility(dto.getVisibility());
        return MarkerMapper.markerToDTO(markerRepository.save(marker));
    }

    @Override
    public MarkerDTO updateMarker(Long id, MarkerDTO dto) {
        Marker marker = markerRepository.findById(id)
                .orElseThrow(() -> new MarkerNotFoundException("Marker not found!"));
        marker.setTitle(dto.getTitle());
        marker.setImageUrl(dto.getImageUrl());
        marker.setAudioUrl(dto.getAudioUrl());
        marker.setAmplitudes(dto.getAmplitudes());
        marker.setIcon(dto.getIcon());

        return MarkerMapper.markerToDTO(markerRepository.save(marker));
    }

    @Override
    public void deleteMarker(Long id, User user) {
        Marker marker = markerRepository.findByIdAndAuthor_Id(id, user.getId())
                .orElseThrow(() -> new MarkerNotFoundException("Marker not found!"));
        markerRepository.delete(marker);
    }

    @Override
    public List<MarkerDTO> searchMarkersByTitle(Long userId, String query) {
        return markerRepository.searchVisibleMarkers(userId, query).stream()
                .map(MarkerMapper::markerToDTO)
                .toList();
    }
}