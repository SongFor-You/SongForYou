package com.example.SongForyou.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class PlaylistRootDto {

    private List<PlaylistDto> playlists;
}
