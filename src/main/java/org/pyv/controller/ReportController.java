package org.pyv.controller;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.ReportMarkerDTO;
import org.pyv.service.MarkerService;
import org.pyv.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/marker")
    public ResponseEntity<Void> reportMarker(@RequestBody ReportMarkerDTO dto) {
        reportService.reportMarker(dto);
        return ResponseEntity.ok().build();
    }
}
