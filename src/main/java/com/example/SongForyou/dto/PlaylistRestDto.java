package com.example.SongForyou.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class PlaylistRestDto {
    private String collectionId;

    // collectionId updt_date tags songs plylst_title like_cnt id
    private String updt_date;
    private List<String> tags;
    private List<Integer> songs;
    private String plylst_title;
    private int like_cnt;
    private int id;
}
