package com.example.SongForyou.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "click_collection")
public class Click {
    @Id
    private String collectionId;

    private String type; // "SONG", "PLAYLIST"
    private int itemId;
    private String userId;

}
