package com.goormthon.backend.service;

import com.goormthon.backend.dto.CreateSpotRequest;
import com.goormthon.backend.dto.SpotResponse;
import com.goormthon.backend.entity.SpotInfo;
import com.goormthon.backend.repository.SpotInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpotInfoService {

    private final SpotInfoRepository repository;

    public Long createSpot(CreateSpotRequest req) {

        SpotInfo spot = SpotInfo.builder()
                .spotName(req.getSpotName())
                .address(req.getAddress())
                .storyTitle(req.getStoryTitle())
                .storyContent(req.getStoryContent())
                .imageUrls(req.getImageUrls())
                .build();

        repository.save(spot);
        return spot.getId();
    }

    public SpotResponse getSpot(Long spotId) {
        SpotInfo spot = repository.findById(spotId)
                .orElseThrow(() -> new IllegalArgumentException("해당 스팟이 존재하지 않습니다. id=" + spotId));

        return SpotResponse.builder()
                .spotId(spot.getId())
                .spotName(spot.getSpotName())
                .address(spot.getAddress())
                .storyTitle(spot.getStoryTitle())
                .storyContent(spot.getStoryContent())
                .imageUrls(spot.getImageUrls())
                .build();
    }
}
