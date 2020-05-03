package com.rcelik.sia.chaptereight.security;


import com.rcelik.sia.chaptereight.data.UserRepository;
import com.rcelik.sia.chaptereight.domain.User;
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
        log.debug("[UserService] instance is created");
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.debug("[loadUserByUsername] userName: {}", userName);

        User user = userRepository.findUserByUserName(userName);
        if (user == null)
            throw new UsernameNotFoundException("User with name not found: " + userName);

        return user;
    }
}
