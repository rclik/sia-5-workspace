//package com.rcelik.sia.chapterfour.tacocloud.configuration.auth;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configurable
//@EnableWebSecurity
//public class InMemorySecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("rahman").password("{noop}rahman").authorities("ROLE_USER").and()
//        .withUser("admin").password("{noop}admin").authorities("ROLE_ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//        super.configure(http);
//    }
//}
