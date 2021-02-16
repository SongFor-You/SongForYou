package com.example.SongForyou.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_collection")
public class User {
    @Id
    private String collectionId;

    private String id;
}
