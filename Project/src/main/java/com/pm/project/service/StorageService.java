package com.pm.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class StorageService {

    private final S3Client s3Client;

    @Value("${supabase.s3.bucket}")
    private String bucket;

    @Value("${supabase.s3.endpoint}")
    private String supabaseEndpoint;

    public StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file, String fileName) throws IOException {
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .acl("public-read")
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return String.format("%s/storage/v1/object/public/%s/%s",
                supabaseEndpoint,
                bucket,
                fileName);
    }
}
