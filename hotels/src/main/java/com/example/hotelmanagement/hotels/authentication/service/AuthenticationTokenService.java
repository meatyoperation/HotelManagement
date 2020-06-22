package com.example.hotelmanagement.hotels.authentication.service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.example.hotelmanagement.hotels.model.User;
import com.example.hotelmanagement.hotels.repository.TestDatabase;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationTokenService {

	private Map<String, User> users = TestDatabase.getUsers();
	public AuthenticationTokenService() {
		super();
	}

	public String issueToken(User user) {
		
		 ZonedDateTime issuedDate = ZonedDateTime.now();
	     ZonedDateTime expirationDate = issuedDate.plusSeconds(180);
		 return Jwts.builder()
	                .setId(UUID.randomUUID().toString())
	                .setIssuer("http://localhost:8670/hotels/webapi")
	                .setAudience("http://localhost:8670/hotels/webapi")
	                .setSubject(user.getUsername())
	                .setIssuedAt(Date.from(issuedDate.toInstant()))
	                .setExpiration(Date.from(expirationDate.toInstant()))
	                .claim("authorities", user.getAuthorities())
	                .claim("refreshCount", 0)
	                .claim("refreshLimit", 1)
	                .signWith(SignatureAlgorithm.HS256, "secret")
	                .compact();
	}

	public User parseToken(String token) {
		//Implement under try/catch block
            Claims claims = Jwts.parser()
                    .setSigningKey("secret")
                    .requireAudience("http://localhost:8670/hotels/webapi")
                    .setAllowedClockSkewSeconds(10)
                    .parseClaimsJws(token)
                    .getBody();          
            return users.get(claims.getSubject());
	}

}
