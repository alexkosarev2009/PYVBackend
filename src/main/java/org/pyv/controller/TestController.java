package org.pyv.controller;

import lombok.RequiredArgsConstructor;
import org.pyv.service.S3Service;
import org.pyv.service.impl.S3ServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.s3.S3Client;

@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class TestController {

    private final S3Service s3Service;

    @GetMapping("/test")
    public String test() {
        return s3Service.listBuckets().toString();
    }
}
