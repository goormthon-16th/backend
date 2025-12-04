package com.goormthon.backend.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class SpotResponse {

    private Long spotId;
    private String spotName;
    private String address;
    private String storyTitle;
    private String storyContent;
    private List<String> imageUrls;
}
