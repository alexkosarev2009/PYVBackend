package org.pyv.service.impl;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.ReportMarkerDTO;
import org.pyv.entity.Marker;
import org.pyv.entity.Report;
import org.pyv.repository.MarkerRepository;
import org.pyv.repository.ReportRepository;
import org.pyv.service.ReportService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final MarkerRepository markerRepository;

    @Override
    public void reportMarker(ReportMarkerDTO dto) {
        Report report = new Report();
        Marker marker = markerRepository.findById(dto.getMarkerId()).orElseThrow();
        report.setMarker(marker);
        report.setReason(dto.getReason());
        reportRepository.save(report);
    }
}
