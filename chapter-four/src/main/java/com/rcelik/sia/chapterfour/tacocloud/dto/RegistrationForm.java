package com.rcelik.sia.chapterfour.tacocloud.dto;

import com.rcelik.sia.chapterfour.tacocloud.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    // lombok agore argumented constructor da sira onemli, class instance property leri sirasi
    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username,passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
    }
}
