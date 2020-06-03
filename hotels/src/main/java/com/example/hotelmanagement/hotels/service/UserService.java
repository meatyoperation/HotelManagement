package com.example.hotelmanagement.hotels.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.hotelmanagement.hotels.model.Authority;
import com.example.hotelmanagement.hotels.model.User;
import com.example.hotelmanagement.hotels.repository.TestDatabase;

public class UserService {
	
	
	private Map<String, User> users = TestDatabase.getUsers();
	public static UserService obj = new UserService();
	public static UserService getUserService() {
		if(obj == null) {
			return new UserService();
		}
		else {
			return obj;
		}
	}
	
	private UserService() {
		
		User user1 = new User("user1","password");
		Set<Authority> authority1 = new HashSet<Authority>();
		authority1.add(Authority.ADMIN);
		authority1.add(Authority.USER);
		user1.setAuthorities(authority1);
		
		User adminUser = new User("admin","password");
		Set<Authority> authority2 = new HashSet<Authority>();
		authority2.add(Authority.ADMIN);
		adminUser.setAuthorities(authority2);
		
		User user3 = new User("user3","password");
		Set<Authority> authority3 = new HashSet<Authority>();
		authority3.add(Authority.USER);
		user3.setAuthorities(authority3);
		
		users.put(user1.getUsername(), user1);
		users.put(adminUser.getUsername(), adminUser);
		users.put(user3.getUsername(), user3);
	}
	
}
