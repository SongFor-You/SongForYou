package com.example.SongForyou.controller;
import com.example.SongForyou.domain.Song;
import com.example.SongForyou.service.MongoInputService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ApiController {

    private final MongoInputService mongoInputService;

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
}
