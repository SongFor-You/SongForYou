package com.example.SongForyou.service;

import com.example.SongForyou.domain.Song;
import com.example.SongForyou.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Pageable;

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

    public List<Song> getSongs(int page, int size) {


        // 음악의 수가 너무 많아서 페이징 처리 한다.
        Pageable pageable=PageRequest.of(page, size);
        Page<Song> page1=songRepository.findAll(pageable);
        List<Song> lists=page1.getContent();
        return lists;

        // 페이징 처리 하지 않고 리스트 전체 조회
        //return songRepository.findAll();
    }

    public Optional<Song> getSongItem(int id) {
        return songRepository.findBySongId(id);

    }
}
