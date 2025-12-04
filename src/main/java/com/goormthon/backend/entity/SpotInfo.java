package com.goormthon.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpotInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spotName;

    private String address;

    private String storyTitle;

    @Column(columnDefinition = "TEXT")
    private String storyContent;

    @ElementCollection
    @CollectionTable(
            name = "spot_info_images",
            joinColumns = @JoinColumn(name = "spot_info_id")
    )
    @Column(name = "image_url")
    private List<String> imageUrls;
}
