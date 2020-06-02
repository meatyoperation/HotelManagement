package com.example.hotelmanagement.hotels.service;

import java.util.Map;

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
		
		User user1 = new User("user","password");
		users.put(user1.getUsername(), user1);
	}
	
}
