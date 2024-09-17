package com.example.yourplace.service;

import com.example.yourplace.document.UserEntity;
import com.example.yourplace.dto.UserDto;
import com.example.yourplace.mapper.UserMapper;
import com.example.yourplace.repostory.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
//    @Autowired
//    UserMapper mapper;
    @Autowired
    PasswordEncoder password;
    @Autowired
    MongoTemplate template;
    @PostConstruct
    public String init(){
        Query query=new Query();
        if (template.count(query, UserEntity.class)==0){
            UserEntity user=UserEntity.builder().username("admin").password("password").roles("admin").build();
            return create(user);
        }

        return "";
    }
    public String create(UserEntity dto){
        dto.setPassword(password.encode(dto.getPassword()));
        return template.save(dto).getId();
    }
}

