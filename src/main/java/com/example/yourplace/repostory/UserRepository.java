package com.example.yourplace.repostory;

import com.example.yourplace.document.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity,String> {
}
