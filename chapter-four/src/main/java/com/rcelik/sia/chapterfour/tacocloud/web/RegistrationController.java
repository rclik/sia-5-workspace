package com.rcelik.sia.chapterfour.tacocloud.web;

import com.rcelik.sia.chapterfour.tacocloud.dto.RegistrationForm;
import com.rcelik.sia.chapterfour.tacocloud.model.User;
import com.rcelik.sia.chapterfour.tacocloud.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // this password encoder will come from CustomUserServiceSecurityConfig
    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        log.debug("[RegistrationController] controller is created");
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public final String getRegistrationForm(){
        log.debug("[getRegistrationForm] is called");
        return "registrationForm";
    }

    @PostMapping
    public final String processRegistration(RegistrationForm registrationForm){
        log.debug("[processRegistration] registration: " + registrationForm);
        User user = userRepository.save(registrationForm.toUser(passwordEncoder));

        log.debug("User is saved successfully: {}", user);
        return "redirect:/login";
    }
}
