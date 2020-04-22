package com.example.demo.security;

public enum UserAuthority {

    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String authority;

    UserAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

}
