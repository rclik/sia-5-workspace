package com.rcelik.sia.chaptereight.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        log.debug("[SecurityConfig] instance is created");
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("[configure] HttpSecurity");
        http.authorizeRequests()
                .antMatchers("/design/**", "/orders/**").hasAuthority("USER_ROLE")
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.PATCH, "/ingredients").permitAll()

                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // disabling csrf for these url s. H2 console needs it
                .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**", "/ingredients/**", "/design", "/orders/**")

//                // configuring login page
//                .and()
//                .formLogin()
//                .loginPage("/login")
//
//                // configuring realm
//                .and()
//                .httpBasic()
//                .realmName("Taco Cloud")
//
//                // configuring logout operation
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
        ;
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.debug("[configure] AuthenticationManagerBuilder");
        auth.userDetailsService(userService).passwordEncoder(encoder());
    }

    @Bean
    public PasswordEncoder encoder(){
        log.debug("[encoder] bean is created");
        return NoOpPasswordEncoder.getInstance();
    }
}
