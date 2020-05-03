package com.rcelik.sia.chapterfour.tacocloud.service.user;

import com.rcelik.sia.chapterfour.tacocloud.model.User;
import com.rcelik.sia.chapterfour.tacocloud.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// bu service class i Spring in UserDetailsService interface ini implement edebilir.
// cunku spring secutiry nin kullandigi UsernameNotFoundException atar. bu sekilde user service imiz spring secutiry
// ye daha uygun hale gelmis olur.
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if (user == null)
            throw new UsernameNotFoundException(String.format("User with name %s not found", userName));

        return user;
    }
}
