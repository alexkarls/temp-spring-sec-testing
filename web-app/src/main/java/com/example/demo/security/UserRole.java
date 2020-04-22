package com.example.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.demo.security.UserAuthority.USER_READ;
import static com.example.demo.security.UserAuthority.USER_WRITE;


public enum UserRole {
    ADMIN(Stream.of(USER_READ, USER_WRITE).collect(Collectors.toSet())),
    USER(Stream.of(USER_READ).collect(Collectors.toSet()));

    private final Set<UserAuthority> authorities;

    UserRole(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }

    /*
    Note to self: Authorities are used in permission based authorization.
    Used in UserDetails (User extends UserDetails) and Spring User.Builder,
    since it exists in the "principal user" it should be implemented.
    However, one could also see each role as a permission, and in this case I see no need...
     */
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}