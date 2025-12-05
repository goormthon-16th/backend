package com.goormthon.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Uploader {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner; // 1. Presigner 주입 추가

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile file, String folderName) throws IOException {

        String fileName = folderName + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build();

        // 파일 업로드 실행
        s3Client.putObject(
                putObjectRequest,
                RequestBody.fromBytes(file.getBytes())
        );

        // 2. 업로드 후 바로 볼 수 있는 Presigned URL 반환
        return getPresignedUrl(fileName);
    }

    // 프라이빗 버킷의 파일에 접근 가능한 임시 URL 생성 메서드
    private String getPresignedUrl(String key) {
        // 어떤 파일에 접근할지 정의
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        // 서명 요청 (유효 기간 설정: 예시는 10분)
        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .getObjectRequest(getObjectRequest)
                .build();

        // URL 생성
        return s3Presigner.presignGetObject(presignRequest).url().toString();
    }
}