package com.example.hotelmanagement.hotels.resource;

import java.net.URI;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.example.hotelmanagement.hotels.authentication.Secured;
import com.example.hotelmanagement.hotels.model.Hotel;
import com.example.hotelmanagement.hotels.service.HotelService;

@Secured
@Path("/hotels")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HotelResource {
	
		HotelService hotelService = HotelService.getHotelService();
				
//	   	@GET
//	    public List<Hotel> getAll() {
//	        return hotelService.getAllHotels();
//	    }
		
	   	@GET
	   	
	   	@PermitAll
	    public Response getAll(@QueryParam("price") int price,@QueryParam("country") String country) {
	   		GenericEntity<List<Hotel>> list;
	   		if(price > 0 && country==null) {
	   			list = new GenericEntity<List<Hotel>>(hotelService.getAllHotelsByPrice(price)) {};
	   			return Response.ok().entity(list).build();
	   		}
	   		else if(price > 0 && country!=null) {
	   			list = new GenericEntity<List<Hotel>>(hotelService.getAllHotelsOfCountryByPrice(country, price)) {};
	   			return Response.ok(list).build();
	   		}
	   		
	   		list = new GenericEntity<List<Hotel>>(hotelService.getAllHotels()) {};
	        return Response.status(Status.OK).entity(list).build();
	    }
		
//	   	@GET
//	   	@Path("country/{countryId}")
//	   	public List<Hotel> recommendedHotels(@PathParam("countryId") String country,@MatrixParam("price") int price, @MatrixParam("ratings") int ratings){
//	   		return hotelService.recommendedHotels(country,price,ratings);
//	   	}
	   	
	   	@GET
	   	@Path("country/{countryId}")
	   	public Response recommendedHotels(@BeanParam HotelFilterBean hotelFilterBean){
	   		GenericEntity<List<Hotel>> list = 
	   				new GenericEntity<List<Hotel>>(hotelService.recommendedHotels(hotelFilterBean.getCountry(),hotelFilterBean.getPrice(),hotelFilterBean.getRatings())) {};
	   		return Response.ok(list).build();
	   	}
	   	
	   	@GET
	   	@Path("/{hotelId}") 
	   	
	   	@RolesAllowed({"USER"})
	   	public Response getHotel(@PathParam("hotelId") String id) {
	   		return Response.ok(hotelService.getHotelById(id)).build();
	   	}
	   	
	   	@POST
	   	
	   	@RolesAllowed({"ADMIN","USER"})
	   	public Response add(Hotel hotel, @Context UriInfo uriInfo) {
	   		Hotel hotelEntity = hotelService.addHotel(hotel);
	   		URI uri = uriInfo.getAbsolutePathBuilder().path(hotelEntity.getId()).build();
	   		return Response.created(uri)
	   				.entity(hotelEntity)
	   				.build();
	   	}
	   	
	   	@PUT
	   	@Path("/{hotelId}")
	   	public Response update(@PathParam("hotelId") String id, Hotel hotel) {
	   		hotel.setId(id);
	   		return Response.status(Status.CREATED).entity(hotelService.updateHotel(hotel)).build();
	   	}
	   	
	   	@DELETE
	   	
	   	@RolesAllowed({"ADMIN"})
	   	@Path("/{hotelId}")
	   	public Response deleteHotel(@PathParam("hotelId") String id) {
	   		hotelService.deleteHotel(id);	   
	   		return Response.noContent().build();
	   	}
}
