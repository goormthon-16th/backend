package com.goormthon.backend.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class CreateSpotRequest {
    private String spotName;
    private String address;
    private String storyTitle;
    private String storyContent;
    private List<String> imageUrls;
}
