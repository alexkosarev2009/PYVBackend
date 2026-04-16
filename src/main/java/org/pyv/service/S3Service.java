package org.pyv.service;

import org.pyv.dto.PresignResponseDTO;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.util.List;

public interface S3Service {
    List<Bucket> listBuckets();
    PresignResponseDTO generatePresignedUrl(String fileName, String contentType);
}
