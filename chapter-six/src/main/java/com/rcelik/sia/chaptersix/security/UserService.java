package com.rcelik.sia.chaptersix.security;

import com.rcelik.sia.chaptersix.data.UserRepository;
import com.rcelik.sia.chaptersix.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        log.debug("[UserService]");
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.debug("[loadUserByUsername] userName: {}", userName);
        User user = userRepository.findByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("User name not found: " + userName);
        }

        return user;
    }
}
