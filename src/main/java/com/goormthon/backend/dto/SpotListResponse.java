package com.goormthon.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpotListResponse {
    private Long spotId;
    private String spotName;
    private String storyTitle;
}
