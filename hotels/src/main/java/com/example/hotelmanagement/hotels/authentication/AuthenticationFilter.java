package com.example.hotelmanagement.hotels.authentication;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Priority;
import javax.ws.rs.ext.Provider;

import com.example.hotelmanagement.hotels.authentication.service.AuthenticationTokenService;
import com.example.hotelmanagement.hotels.model.User;
import com.example.hotelmanagement.hotels.repository.TestDatabase;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	
    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader
                            .substring(AUTHENTICATION_SCHEME.length()).trim();

        try {

            // Validate the token
            
            // Parse token for user related details
        	AuthenticationTokenService authenticationTokenService = new AuthenticationTokenService();
        	User userDetails = authenticationTokenService.parseToken(token);
            
            boolean isSecure = requestContext.getSecurityContext().isSecure();
            // Overriding security context for incoming request
            SecurityContext securityContext = new TokenBasedSecurityContext(isSecure, userDetails);
            requestContext.setSecurityContext(securityContext);

        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }
    }

	private boolean isTokenBasedAuthentication(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                    .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, 
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }

}