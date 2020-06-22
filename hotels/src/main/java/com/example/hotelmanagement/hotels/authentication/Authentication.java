package com.example.hotelmanagement.hotels.authentication;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.hotelmanagement.hotels.authentication.service.AuthenticationTokenService;
import com.example.hotelmanagement.hotels.model.User;
import com.example.hotelmanagement.hotels.repository.TestDatabase;
import com.example.hotelmanagement.hotels.service.UserService;

@Path("/authentication")
public class Authentication {
	
	UserService userService = UserService.getUserService();
	private Map<String, User> users = TestDatabase.getUsers();
	
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username, 
                                     @FormParam("password") String password) {

        try {

               // Authenticate the user using the credentials provided
            	authenticate(username, password);

               // Issue a token for the user
            	String token = issueToken(username,password);

               // Return the token on the response
            	return Response.ok(token).build();

        	} catch (Exception e) {
        		return Response.status(Response.Status.FORBIDDEN).build();
        	}      
    }

    private void authenticate(String username, String password) throws Exception {
        if(users.get(username) == null && !(users.get(username).getPassword().equals(password))) {
        	throw new Exception();
        }
    }

    private String issueToken(String username, String password) {
    	AuthenticationTokenService authenticationTokenService = new AuthenticationTokenService();
    	return authenticationTokenService.issueToken(users.get(username));
    }
}