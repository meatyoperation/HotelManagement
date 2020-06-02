package com.example.hotelmanagement.hotels.resource;

import javax.ws.rs.CookieParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.PathParam;

public class HotelFilterBean {

	@HeaderParam("sessionId") 
	private String sessionId;
	
    @CookieParam("cookie") 
    private String cookievalue;
	
	@PathParam("countryId") 
	private String country;
	
	@MatrixParam("price")
	private int price;
	
	@MatrixParam("ratings")
	private int ratings;
	
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getCookievalue() {
		return cookievalue;
	}
	public void setCookievalue(String cookievalue) {
		this.cookievalue = cookievalue;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	
	
}
