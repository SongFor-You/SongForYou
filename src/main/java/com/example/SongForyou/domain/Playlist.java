package com.example.SongForyou.domain;

import com.example.SongForyou.dto.PlaylistDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

//@AllArgsConstructor
//@Builder
@Getter
//@ToString
@NoArgsConstructor
@Document(collection = "playlist_collection")
public class Playlist {

    @Id
    private String collectionId; // compass 에 없다

    // updt_date tags songs plylst_title like_cnt id

    private String updt_date;
    private List<String> tags;

//    @OneToMany(mappedBy = "member")
    private List<Integer> songs;
    private String plylst_title;
    private int like_cnt;
    private int id;

    @Builder
    public Playlist(PlaylistDto dto) {
        this.updt_date = dto.getUpdt_date();
        this.tags = dto.getTags();
        this.songs = dto.getSongs();
        this.plylst_title = dto.getPlylst_title();
        this.like_cnt = dto.getLike_cnt();
        this.id = dto.getId();
    }

}
