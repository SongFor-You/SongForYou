package com.example.SongForyou.controller;

import com.example.SongForyou.domain.Playlist;
import com.example.SongForyou.dto.PlaylistRestDto;
import com.example.SongForyou.dto.PlaylistRootDto;
import com.example.SongForyou.service.MongoInputService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ApiController {

    private final MongoInputService mongoInputService;

    @GetMapping("/api/save/playlist")
    public PlaylistRootDto save() {
//        logger.info("\n"+requestDto.toString());
        return mongoInputService.savePlaylists();
    }

    @GetMapping("/api/playlists")
    public List<PlaylistRestDto> getPlaylists() {
        return mongoInputService.getPlaylists().stream()
                .map(playlist -> PlaylistRestDto.builder()
                        .collectionId(playlist.getCollectionId())
                        .updt_date(playlist.getUpdt_date())
                        .tags(playlist.getTags())
                        .songs(playlist.getSongs())
                        .plylst_title(playlist.getPlylst_title())
                        .like_cnt(playlist.getLike_cnt())
                        .id(playlist.getId())
                        .build()
                ).collect(Collectors.toList());
    }

    @GetMapping("/api/playlists/{id}")
    public PlaylistRestDto getPlaylist(@PathVariable(name="id") int id) {
        return mongoInputService.getPlaylistItem(id);
    }

}
