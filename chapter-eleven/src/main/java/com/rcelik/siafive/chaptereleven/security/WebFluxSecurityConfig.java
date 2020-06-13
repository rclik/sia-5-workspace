package com.rcelik.siafive.chaptereleven.security;

import com.rcelik.siafive.chaptereleven.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Slf4j
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
        return serverHttpSecurity.authorizeExchange()
                .pathMatchers("/hello")
                .hasAuthority("USER")
                .pathMatchers("/design")
                .permitAll()
                .pathMatchers("/bye")
                .hasAuthority("ADMIN")
                .and().build();
    }

    @Bean
    public PasswordEncoder encoder(){
        log.debug("[encoder] bean is created");
        return NoOpPasswordEncoder.getInstance();
    }
}