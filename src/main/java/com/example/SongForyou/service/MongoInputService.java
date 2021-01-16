package com.example.SongForyou.service;

import com.example.SongForyou.domain.Playlist;
import com.example.SongForyou.dto.PlaylistRestDto;
import com.example.SongForyou.dto.PlaylistRootDto;
import com.example.SongForyou.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MongoInputService {

    private final RestTemplate restTemplate;
    private final PlaylistRepository playlistRepository;

    @Transactional
    public PlaylistRootDto savePlaylists() {

        ResponseEntity<PlaylistRootDto> response = restTemplate.exchange(
                "http://127.0.0.1:5000/playlist",
                HttpMethod.GET,
                null,
                PlaylistRootDto.class);
        log.info(Objects.requireNonNull(response.getBody()).toString());

        response.getBody().getPlaylists()
                .forEach(playlistDto -> {
                    // domain 으로 변경하고, 몽고 디비에 저장

                    Playlist build = Playlist.builder()
                            .dto(playlistDto)
                            .build();

                    Playlist save = playlistRepository.save(build);
                    log.info("[Playlist save] "+save.toString());
                });

        return response.getBody();
    }

    public List<Playlist> getPlaylists() {

        return playlistRepository.findAll();
    }

    public PlaylistRestDto getPlaylistItem(int id) {
        Playlist playlist = playlistRepository.findByPlaylistId(id).get();
        return PlaylistRestDto.builder()
                .collectionId(playlist.getCollectionId())
                .updt_date(playlist.getUpdt_date())
                .tags(playlist.getTags())
                .songs(playlist.getSongs())
                .plylst_title(playlist.getPlylst_title())
                .like_cnt(playlist.getLike_cnt())
                .id(playlist.getId())
                .build();
    }
}
