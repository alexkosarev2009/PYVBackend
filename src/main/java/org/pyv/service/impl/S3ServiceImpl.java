package org.pyv.service.impl;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.PresignResponseDTO;
import org.pyv.service.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    @Value("${s3.bucket}")
    private String bucket;

    @Override
    public List<Bucket> listBuckets() {
        return s3Client.listBuckets().buckets();
    }

    @Override
    public PresignResponseDTO generatePresignedUrl(String fileName, String contentType) {
        String key = "markers/" + UUID.randomUUID() + "-" + fileName;

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(contentType)
                .build();

        PutObjectPresignRequest presignRequest =
                PutObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofMinutes(10))
                        .putObjectRequest(objectRequest)
                        .build();

        PresignedPutObjectRequest presignedRequest =
                s3Presigner.presignPutObject(presignRequest);

        return new PresignResponseDTO(
                presignedRequest.url().toString(),
                key
        );
    }
}
