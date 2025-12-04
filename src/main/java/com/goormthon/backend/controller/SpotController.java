package com.goormthon.backend.controller;

import com.goormthon.backend.dto.GenerateStoryRequest;
import com.goormthon.backend.dto.GenerateStoryResponse;
import com.goormthon.backend.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spots")
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @PostMapping("/generate-story")
    public ResponseEntity<GenerateStoryResponse> generateStory(
            @RequestBody GenerateStoryRequest request
    ) {
        GenerateStoryResponse response = spotService.generateStory(request);
        return ResponseEntity.ok(response);
    }
}
