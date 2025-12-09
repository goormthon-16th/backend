package com.goormthon.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;
    private String spotName;
    private String address;

    // 수정됨: 썸네일 URL도 길어질 수 있으므로 TEXT로 변경
    @Column(columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(columnDefinition = "TEXT")
    private String text1;

    @Column(columnDefinition = "TEXT")
    private String imageUrl1;

    @Column(columnDefinition = "TEXT")
    private String text2;

    @Column(columnDefinition = "TEXT")
    private String imageUrl2;

    @Column(columnDefinition = "TEXT")
    private String text3;

    @Column(columnDefinition = "TEXT")
    private String imageUrl3;

    @Column(columnDefinition = "TEXT")
    private String generatedStory;
}