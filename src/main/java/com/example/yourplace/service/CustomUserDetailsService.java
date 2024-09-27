package com.example.yourplace.service;

import com.example.yourplace.document.UserEntity;
import com.example.yourplace.dto.Credetial;
import com.example.yourplace.model.TokenInfo;
import com.example.yourplace.repostory.UserRepository;
import com.example.yourplace.utils.JwUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MongoTemplate template;
    @Autowired
    JwUtils jwUtils;
    @Autowired
    PasswordEncoder password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query=new Query();
        query.addCriteria(Criteria.where("username").is(username));
        UserEntity user=template.findOne(query,UserEntity.class);
        return User.withUsername(username).roles(user.getRoles()).password(user.getPassword()).build();
    }

    public boolean isValid(TokenInfo tokenInfo) {
        Query query= new Query();
        query.addCriteria(Criteria.where("_id").is(tokenInfo.getUserId()));
        query.addCriteria(Criteria.where("username").is(tokenInfo.getUsername()));
        query.addCriteria(Criteria.where("roles").is(tokenInfo.getRoles()));
        return template.exists(query,UserEntity.class );
    }


    public String login(Credetial credetial)  {
        UserEntity user;
        Query query=new Query();
        query.addCriteria(Criteria.where("username").is(credetial.getUsername()));
        try {
            user=template.findOne(query,UserEntity.class);
        }catch (Exception ex){
            throw new RuntimeException("credentaial not found");
        }
        if (!password.matches(credetial.getPassword(), user.getPassword())){
            throw new RuntimeException("credentaial not found");
        }
        return jwUtils.generate(user);
    }
}
