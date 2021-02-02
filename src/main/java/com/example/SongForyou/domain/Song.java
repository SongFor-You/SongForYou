package com.example.SongForyou.domain;

import com.example.SongForyou.dto.SongDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import org.bson.types.ObjectId;

@Getter
@NoArgsConstructor
@Document(collection = "song")
public class Song {
    @Id
    private ObjectId _id;

    private int id;

    private List<String> song_gn_dtl_gnr_basket;
    private String issue_date;
    private String album_name;
    private int album_id;
    private List<Integer> artist_id_basket;
    private String song_name;
    private List<String> song_gn_gnr_basket;
    private List<String> artist_name_basket;

    @Builder
    public Song(SongDto dto) {
        this.id=dto.getId();
        this.issue_date=dto.getIssue_date();
        this.song_gn_dtl_gnr_basket=dto.getSong_gn_dtl_gnr_basket();
        this.album_name=dto.getAlbum_name();
        this.album_id=dto.getAlbum_id();
        this.artist_id_basket=dto.getArtist_id_basket();
        this.song_name=dto.getSong_name();
        this.song_gn_gnr_basket=dto.getSong_gn_gnr_basket();
        this.artist_name_basket=dto.getArtist_name_basket();
    }
}
