package com.rcelik.sia.chapterfour.tacocloud.configuration.auth;

import com.rcelik.sia.chapterfour.tacocloud.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class CustomUserServiceSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public CustomUserServiceSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    // bu bean password encoder icin kullanilacak
    // bean a gelecek olursak SAC da bir kere olusturulur, sonrasinda ise ne zaman istenirse oradan tekrar cagirilir.
    @Bean
    PasswordEncoder getPasswordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(getPasswordEncoderBean());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests().antMatchers("/register", "/login", "/images/**", "/styles.css").permitAll();

        http.authorizeRequests().antMatchers("/design", "/orders").hasAuthority("USER");

        // kendi login page imizi veriyoruz
        http.formLogin().loginPage("/login").defaultSuccessUrl("/design");

        super.configure(http);
    }
}
