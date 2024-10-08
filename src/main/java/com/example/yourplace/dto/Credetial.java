package com.example.yourplace.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Credetial {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
