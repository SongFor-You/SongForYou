package com.example.SongForyou.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class PlaylistDto {

    private String updt_date;
    private List<String> tags;
    private List<Integer> songs;
    private String plylst_title;
    private int like_cnt;
    private int id;
}
