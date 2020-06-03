package com.example.hotelmanagement.hotels.authentication;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import com.example.hotelmanagement.hotels.model.Authority;
import com.example.hotelmanagement.hotels.model.User;

public class TokenBasedSecurityContext implements SecurityContext {

    private final boolean secure;
    private User user;

    public TokenBasedSecurityContext(boolean secure, User user) {
		super();
		this.secure = secure;
		this.user = user;
	}

	@Override
    public Principal getUserPrincipal() {
        return () -> user.getUsername();
    }

    @Override
    public boolean isUserInRole(String role) {
        return user.getAuthorities().contains(Authority.valueOf(role));
    }

    @Override
    public boolean isSecure() {
        return secure;
    }

    @Override
    public String getAuthenticationScheme() {
        return "Bearer";
    }

}
