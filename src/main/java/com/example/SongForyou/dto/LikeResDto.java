package com.example.SongForyou.dto;

import com.example.SongForyou.domain.Like;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LikeResDto {
    private String LikeCollectionId;
    private String type; // "SONG", "PLAYLIST"
    private int itemId;
    private String userId;

    public LikeResDto(Like like) {
        this.LikeCollectionId = like.getCollectionId();
        this.type = like.getType();
        this.itemId = like.getItemId();
        this.userId = like.getUserId();
    }
}
