package org.pyv.service.impl;

import lombok.RequiredArgsConstructor;
import org.pyv.service.S3Service;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.util.List;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {
    private final S3Client s3;

    @Override
    public List<Bucket> listBuckets() {
        return s3.listBuckets().buckets();
    }
}
