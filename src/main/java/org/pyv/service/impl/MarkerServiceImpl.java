package org.pyv.service.impl;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.CreateMarkerDTO;
import org.pyv.dto.MarkerDTO;
import org.pyv.entity.Marker;
import org.pyv.entity.User;
import org.pyv.exception.MarkerNotFoundException;
import org.pyv.exception.UserNotFoundException;
import org.pyv.repository.MarkerRepository;
import org.pyv.repository.UserRepository;
import org.pyv.service.MarkerService;
import org.pyv.util.MarkerMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarkerServiceImpl implements MarkerService {
    private final MarkerRepository markerRepository;
    private final UserRepository userRepository;

    @Override
    public List<MarkerDTO> getAllMarkers() {
        return markerRepository.findAll().stream()
                .map(MarkerMapper::markerToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MarkerDTO getMarkerById(Long id) {
        return markerRepository.findById(id)
                .map(MarkerMapper::markerToDTO)
                .orElseThrow(() -> new MarkerNotFoundException("Marker not found!"));
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
        return MarkerMapper.markerToDTO(markerRepository.save(marker));
    }

    @Override
    public MarkerDTO updateMarker(Long id, MarkerDTO dto) {
        Marker marker = markerRepository.findById(id)
                .orElseThrow(() -> new MarkerNotFoundException("Marker not found!"));
        marker.setTitle(dto.getTitle());
        marker.setImageUrl(dto.getImageUrl());
        marker.setAudioUrl(dto.getAudioUrl());

        return MarkerMapper.markerToDTO(markerRepository.save(marker));
    }

    @Override
    public void deleteMarker(Long id) {
        if (!markerRepository.existsById(id)) {
            throw new MarkerNotFoundException("Marker not found!");
        }
        markerRepository.deleteById(id);
    }
}
