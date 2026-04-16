package org.pyv.controller;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.PresignResponseDTO;
import org.pyv.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @GetMapping("/test")
    public String test() {
        return s3Service.listBuckets().toString();
    }
    @GetMapping("/presign")
    public ResponseEntity<PresignResponseDTO> getPresign(
            @RequestParam String fileName,
            @RequestParam String contentType
    ) {
        return ResponseEntity.ok(s3Service.generatePresignedUrl(fileName, contentType));
    }
}
