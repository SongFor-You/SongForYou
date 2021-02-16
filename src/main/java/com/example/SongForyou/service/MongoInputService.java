package com.example.SongForyou.service;

import com.example.SongForyou.domain.Click;
import com.example.SongForyou.domain.Playlist;
import com.example.SongForyou.domain.Song;
import com.example.SongForyou.domain.User;
import com.example.SongForyou.dto.PalylistAndSongsDto;
import com.example.SongForyou.dto.PlaylistRestDto;
import com.example.SongForyou.dto.PlaylistRootDto;
import com.example.SongForyou.repository.ClickRepository;
import com.example.SongForyou.repository.PlaylistRepository;
import com.example.SongForyou.repository.SongRepository;
import com.example.SongForyou.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MongoInputService {

    private final RestTemplate restTemplate;
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;

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
                    log.info("[Playlist save] " + save.toString());
                });

        return response.getBody();
    }

    public List<PlaylistRestDto> getPlaylists(String page) {
        // TODO size 설정파일로 배기
        PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), 10, Sort.Direction.ASC, "collectionId");
        List<PlaylistRestDto> collect = playlistRepository.findAll(pageRequest).getContent().stream()
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
        return collect;
    }

    private final MongoTemplate mongoTemplate; /// TODO 배치 위초

    public List<PalylistAndSongsDto> getPlaylistsAndSongs(String page) {
        PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), 10, Sort.Direction.ASC, "collectionId");

        List<PalylistAndSongsDto> collect = playlistRepository.findAll(pageRequest).getContent().stream()
                .map(playlist -> {

                    List<Song> songList = new ArrayList<>();
                    for (int id : playlist.getSongs()) {
                        Optional<Song> bySongId = songRepository.findBySongId(id);
                        bySongId.ifPresent(songList::add);
                    }
//                    List<Song> songList = mongoTemplate.find(new Query(where("id").all(playlist.getSongs()))
//                            /*Query.query(Criteria.where("id").all(playlist.getSongs()))*/, Song.class);

                    return PalylistAndSongsDto.builder()
                            .collectionId(playlist.getCollectionId())
                            .updt_date(playlist.getUpdt_date())
                            .tags(playlist.getTags())
                            .songs(songList)
                            .plylst_title(playlist.getPlylst_title())
                            .like_cnt(playlist.getLike_cnt())
                            .id(playlist.getId())
                            .build();

                }).collect(Collectors.toList());

        return collect;
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

    public List<Song> getSongs(int page, int size) {


        // 음악의 수가 너무 많아서 페이징 처리 한다.
        Pageable pageable = PageRequest.of(page, size);
        Page<Song> page1 = songRepository.findAll(pageable);
        List<Song> lists = page1.getContent();
        return lists;

        // 페이징 처리 하지 않고 리스트 전체 조회
        //return songRepository.findAll();
    }

    public Optional<Song> getSongItem(int id) {
        return songRepository.findBySongId(id);
    }

    private final ClickRepository clickRepository;

    @Transactional
    public Click clickItem(String type, int id, String userId) {
        Click build = Click.builder()
                .type(type)
                .itemId(id)
                .userId(userId)
                .build();

        Click save = clickRepository.save(build);
        log.info("[Click save] " + save.toString());

        return save;
    }

    private final UserRepository userRepository;
    public String createUser(String id) {
        User user = User.builder()
                .id(id)
                .build();
        return userRepository.save(user).getId();
    }
}
