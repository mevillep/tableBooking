package solutions.egen.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import solutions.egen.dao.ReservationDAO;
import solutions.egen.exception.AppException;
import solutions.egen.model.Reservation;

@Path("/status")
public class StatusController {
	

	/* 
	 * To check open/closed reservations. Meaning, the reservations that are completed or not completed.
	 * The owner can get a list of all the past and future reservations from here. 
	 * 
	 */
	@GET
	@Path("/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reservation> checkStatus(@PathParam("status") String status){
		
		List<Reservation> reservations= null;
		try {
			ReservationDAO dao = new ReservationDAO();
			
			reservations = dao.checkStatus(status);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		
		return reservations;
		
	} 




}
