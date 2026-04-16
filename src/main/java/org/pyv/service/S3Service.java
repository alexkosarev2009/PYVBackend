package org.pyv.service;

import software.amazon.awssdk.services.s3.model.Bucket;

import java.util.List;

public interface S3Service {
    public List<Bucket> listBuckets();
}
