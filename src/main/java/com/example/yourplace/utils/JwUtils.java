package com.example.yourplace.utils;

import com.example.yourplace.document.UserEntity;
import com.example.yourplace.model.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwUtils {
    public String generate(UserEntity user)
    {
        HashMap<String, Object> claims=new HashMap<>();
        claims.put("username",user.getUsername());
        claims.put("userId",user.getId());
        claims.put("roles",user.getRoles());
         return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+(24+60+60+1000)))
                .compact();
    }
    public Boolean isValid(String token)
    {
        Claims claims=Jwts.parserBuilder().build().parseClaimsJws(token).getBody();
        if (claims.getExpiration().getTime()<System.currentTimeMillis()) {
            return false;
        }
        if (claims.getIssuedAt().getTime()>System.currentTimeMillis()) {
            return false;
        }
        return true;
    }
    public TokenInfo extractInfo(String token)
    {
        Claims claims=Jwts.parserBuilder().build().parseClaimsJws(token).getBody();
        return TokenInfo.builder().username(claims.get("username").toString())
                .userId(claims.get("userId").toString())
                .roles(claims.get("roles").toString()).
                issuedAt(claims.getIssuedAt()).
                expaired(claims.getExpiration())
                .build();
    }
}
