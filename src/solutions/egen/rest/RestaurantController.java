package solutions.egen.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import solutions.egen.dao.CustomerDAO;
import solutions.egen.dao.RestaurantDAO;
import solutions.egen.exception.AppException;
import solutions.egen.model.Customer;
import solutions.egen.model.Restaurant;

@Path("/restaurant")
public class RestaurantController {

	/*
	 * Owner can get restaurant details
	 * 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Restaurant find(){
		Restaurant restaurant= null;
		try {
			RestaurantDAO dao = new RestaurantDAO();
			
			restaurant = dao.find();
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return restaurant;
	}
	
	
	/*
	 * Owner can update the restaurant details. I don't know why he would want to update the restaurant name and address.
	 * 
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Restaurant update(@PathParam("id") int id, Restaurant restaurant){
	
		try {
			RestaurantDAO dao = new RestaurantDAO();
			 restaurant = dao.update(id, restaurant);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		

		return restaurant;
		

		
	}
}
