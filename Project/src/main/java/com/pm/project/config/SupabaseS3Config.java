package com.pm.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class SupabaseS3Config {

    @Value("${supabase.s3.endpoint}")
    private String endpoint;

    @Value("${supabase.s3.accessKey}")
    private String accessKey;

    @Value("${supabase.s3.secretKey}")
    private String secretKey;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .region(Region.US_EAST_1) // Region does not matter for Supabase, but required by SDK
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true) // use path-style URLs
                        .build())
                .httpClientBuilder(UrlConnectionHttpClient.builder())
                .build();
    }
}
