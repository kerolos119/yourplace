package com.example.yourplace.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenInfo {
    private String username;
    private String userId;
    private String roles;
    private Date issuedAt;
    private Date expaired;
}
