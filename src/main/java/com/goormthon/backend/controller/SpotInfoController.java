package com.goormthon.backend.controller;

import com.goormthon.backend.dto.CreateSpotRequest;
import com.goormthon.backend.dto.CreateSpotResponse;
import com.goormthon.backend.dto.SpotResponse;
import com.goormthon.backend.service.SpotInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SpotInfoController {

    private final SpotInfoService service;

    @PostMapping("/spots")
    public ResponseEntity<CreateSpotResponse> createSpot(
            @RequestBody CreateSpotRequest req
    ) {
        Long spotId = service.createSpot(req);

        return ResponseEntity
                .status(201)
                .body(new CreateSpotResponse(spotId));
    }


    @GetMapping("/spots/{spotId}")
    public ResponseEntity<SpotResponse> getSpot(
            @PathVariable Long spotId
    ) {
        SpotResponse response = service.getSpot(spotId);
        return ResponseEntity.ok(response);
    }
}
