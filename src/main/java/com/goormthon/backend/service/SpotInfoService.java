package com.goormthon.backend.service;

import com.goormthon.backend.dto.CreateSpotRequest;
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
}
