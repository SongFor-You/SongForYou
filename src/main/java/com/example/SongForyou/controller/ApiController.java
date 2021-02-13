package com.example.SongForyou.controller;

import com.example.SongForyou.domain.Click;
import com.example.SongForyou.domain.Song;
import com.example.SongForyou.domain.User;
import com.example.SongForyou.dto.ClickResDto;
import com.example.SongForyou.dto.PalylistAndSongsDto;
import com.example.SongForyou.dto.PlaylistRestDto;
import com.example.SongForyou.dto.PlaylistRootDto;
import com.example.SongForyou.repository.UserRepository;
import com.example.SongForyou.service.MongoInputService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
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
    public List<PlaylistRestDto> getPlaylists(@RequestParam("page") String page) {
        return mongoInputService.getPlaylists(page);
    }

    @GetMapping("/api/playlists-songs")
    public List<PalylistAndSongsDto> getPlaylistsAndSongs(@RequestParam("page") String page) {
        return mongoInputService.getPlaylistsAndSongs(page);
    }


    @GetMapping("/api/playlists/{id}")
    public PlaylistRestDto getPlaylist(@PathVariable(name="id") int id) {
        return mongoInputService.getPlaylistItem(id);
    }

    @GetMapping("/api/songs")
    public List<Song> getSongs(@RequestParam("page") int page, @RequestParam("size") int size) {

        return mongoInputService.getSongs(page, size);
    }

    @GetMapping("/api/song/{id}")
    public Optional<Song> getSong(@PathVariable(name="id") int id) {
        return mongoInputService.getSongItem(id);
    }
    @GetMapping("/api/hi")
    public String Hello() {
        // 제대로 돌아가는지 확인차...
        return "Hi";
    }

    @GetMapping("/api/click/playlists/{id}")
    public ClickResDto clickPlaylist(@PathVariable(name="id") int id, @RequestParam("userId") String userId) {
        Click clickItem = mongoInputService.clickItem("PLAYLIST"/*type*/, id, userId);
        return new ClickResDto(clickItem);
    }

    @GetMapping("/api/click/songs/{id}")
    public ClickResDto clickSong(@PathVariable(name="id") int id, @RequestParam("userId") String userId) {
        // 카프카 로직
        Click clickItem =  mongoInputService.clickItem("SONG", id, userId);
        return new ClickResDto(clickItem);
    }

//    private String getUserId(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        // TODO id 있는지 확인하는 로직 추가해야함. 있으면 그대로 쓰고, 없으면 생성 , 그냥 click 에 함께 저장
          // 데이터 적재하니까 괜찮을듯 안되면 그때 user 생성하는 로직 넣기
//        String userId = mongoInputService.createUser(session.getId());
//        return userId;
//    }

}
