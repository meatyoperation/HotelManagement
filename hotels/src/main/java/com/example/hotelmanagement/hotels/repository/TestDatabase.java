package com.example.hotelmanagement.hotels.repository;

import java.util.HashMap;
import java.util.Map;

import com.example.hotelmanagement.hotels.model.Hotel;
import com.example.hotelmanagement.hotels.model.Review;
import com.example.hotelmanagement.hotels.model.User;

public class TestDatabase {
		
	private static Map<String, Hotel> hotels = new HashMap<>();
	private static Map<String, Review> reviews = new HashMap<>();
	private static Map<String, User> usersToken = new HashMap<>();
	private static Map<String, User> users = new HashMap<>();
	
	public static Map<String, Hotel> getHotels() {
		return hotels;
	}
	public static Map<String, Review> getReviews() {
		return reviews;
	}
	
	public static Map<String, User> getUsersToken(){
        return usersToken;
	}
	public static Map<String, User> getUsers() {
		return users;
	}
	
	
}
