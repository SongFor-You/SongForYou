package com.example.SongForyou.service;

import com.example.SongForyou.domain.Song;
import com.example.SongForyou.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MongoInputService {

    private final RestTemplate restTemplate;

    @Autowired
    private final SongRepository songRepository;

    public List<Song> getSongs() {

        return songRepository.findAll();
    }

    public Optional<Song> getSongItem(int id) {
        return songRepository.findBySongId(id);

    }
}
