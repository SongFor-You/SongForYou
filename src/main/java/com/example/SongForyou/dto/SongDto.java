package com.example.SongForyou.dto;

import javafx.scene.NodeBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class SongDto {
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

}
