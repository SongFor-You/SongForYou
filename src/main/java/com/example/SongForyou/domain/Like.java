package com.example.SongForyou.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="like_collection")
public class Like {
    @Id
    private String collectionId;

    private String type;
    private int itemId;
    private String userId;
}
