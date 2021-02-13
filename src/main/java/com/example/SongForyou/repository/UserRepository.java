package com.example.SongForyou.repository;

import com.example.SongForyou.domain.Click;
import com.example.SongForyou.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
