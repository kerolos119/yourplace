package com.example.yourplace.controller;

import com.example.yourplace.dto.Credetial;
import com.example.yourplace.model.TokenInfo;
import com.example.yourplace.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class LoginController {
    @Autowired
    CustomUserDetailsService userDetailsService;
    @PostMapping
    public String login(@RequestBody @Valid Credetial credetial){
        return userDetailsService.login(credetial);
    }
}
