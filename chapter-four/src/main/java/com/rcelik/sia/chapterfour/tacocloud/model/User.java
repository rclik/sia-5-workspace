package com.rcelik.sia.chapterfour.tacocloud.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

// lombok u class da activate edilmesi icin.
@Data

// jpa kullanacagi icin no argument constructor i olmasi lazim.
// bunu public yapmazsak calismiyor, onun icin access level inin public ettik.
// force ile ise class instance lerinin default sekilde initialize edilmesini sagladik.
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)

// argumanli constructor olusturmak icin
@RequiredArgsConstructor

// bu class in jpa entity class i oldugunu SAC a soylemek icin
@Entity
public class User implements UserDetails {

    // bu class in UserDetails interface ini implement etmesi gerekiyor. cunku spring security i
    // bu class ile kullanabilmek icin object in bu method lara sahip olmasi gerekiyor.

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private final String userName;
    private final String password;
    private final String fullName;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phoneNumber;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
