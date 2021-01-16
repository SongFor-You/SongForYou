package com.example.SongForyou.repository;

import com.example.SongForyou.domain.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends MongoRepository<Playlist,String> { // 또는 String

//    @Query(value = "select p from Playlist p where p.id = :id")
    @Query(value="{ 'id' : ?0 }")
    Optional<Playlist> findByPlaylistId(int songId);

}
