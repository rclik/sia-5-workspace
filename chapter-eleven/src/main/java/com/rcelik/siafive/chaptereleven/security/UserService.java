package com.rcelik.siafive.chaptereleven.security;

import com.rcelik.siafive.chaptereleven.data.UserRepository;
import com.rcelik.siafive.chaptereleven.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        log.debug("[UserService] instance is created");
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("[loadUserByUsername] userName: {}", username);
        Optional<User> user = userRepository.findUserByUserName(username);

        return user.orElseThrow(() -> new UsernameNotFoundException(String.format("User with name: %s not found", username)));
    }
}
