package com.example.SongForyou.repository;

import com.example.SongForyou.domain.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface SongRepository extends MongoRepository<Song, Long>{

    @Query(value="{ 'id' : ?0 }")
    Optional<Song> findBySongId(int songId);

    //List<Song> findAll(Pageable pageable);

}
