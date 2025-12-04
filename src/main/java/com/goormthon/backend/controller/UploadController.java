//package com.goormthon.backend.controller;
//
//import com.goormthon.backend.service.S3Uploader;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Map;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/upload")
//public class UploadController {
//
//    private final S3Uploader s3Uploader;
//
//    @PostMapping("/image")
//    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
//
//        String url = s3Uploader.upload(file, "images");
//
//        return ResponseEntity.ok(Map.of("url", url));
//    }
//}
