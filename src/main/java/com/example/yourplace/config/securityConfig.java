package com.example.yourplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.authorizeHttpRequests(auth->{auth.anyRequest().authenticated();

        }).formLogin(form->{
            form.successForwardUrl("http://localhost:8080/swagger-ui/index.html");
        });
        return security.build();
    }
    @Bean
    public UserDetailsService service(){
        UserDetails userDetails= User.withUsername("test").password(passwordEncoder().encode("test")).build();
        return  new  InMemoryUserDetailsManager(userDetails);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
