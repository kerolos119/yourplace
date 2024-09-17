package com.example.yourplace.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    private String id;
    private String username;
    private String password;
    private String roles;
}
