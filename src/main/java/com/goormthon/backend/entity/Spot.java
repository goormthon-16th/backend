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
    private String thumbnailUrl;

    @Column(columnDefinition = "TEXT")
    private String text1;
    private String imageUrl1;

    @Column(columnDefinition = "TEXT")
    private String text2;
    private String imageUrl2;

    @Column(columnDefinition = "TEXT")
    private String text3;
    private String imageUrl3;

    @Column(columnDefinition = "TEXT")
    private String generatedStory;
}
