package com.example.SongForyou.repository;

import com.example.SongForyou.domain.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepository extends MongoRepository<Like,String> {
}
