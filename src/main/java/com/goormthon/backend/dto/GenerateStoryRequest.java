package com.goormthon.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateStoryRequest {

    private String ownerName;
    private String spotName;
    private String address;

    private String text1;
    private String text2;
    private String text3;
}
