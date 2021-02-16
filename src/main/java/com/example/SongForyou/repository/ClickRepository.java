package com.example.SongForyou.repository;

import com.example.SongForyou.domain.Click;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClickRepository extends MongoRepository<Click,String> {
}
