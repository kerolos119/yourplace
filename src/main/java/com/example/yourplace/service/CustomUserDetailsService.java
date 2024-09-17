package com.example.yourplace.service;

import com.example.yourplace.document.UserEntity;
import com.example.yourplace.repostory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MongoTemplate template;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query=new Query();
        query.addCriteria(Criteria.where("username").is(username));
        UserEntity user=template.findOne(query,UserEntity.class);
        return User.withUsername(username).roles(user.getRoles()).password(user.getPassword()).build();
    }
}
