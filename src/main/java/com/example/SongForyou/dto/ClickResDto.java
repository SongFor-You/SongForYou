package com.example.SongForyou.dto;

import com.example.SongForyou.domain.Click;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClickResDto {
    private String clickCollectionId;
    private String type; // "SONG", "PLAYLIST"
    private int itemId;
    private String userId;

    public ClickResDto(Click click) {
        this.clickCollectionId = click.getCollectionId();
        this.type = click.getType();
        this.itemId = click.getItemId();
        this.userId = click.getUserId();
    }
}
