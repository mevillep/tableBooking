package solutions.egen.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import solutions.egen.dao.ReservationDAO;
import solutions.egen.exception.AppException;
import solutions.egen.model.Customer;
import solutions.egen.model.Reservation;
import solutions.egen.model.Table;


@Path("/createReservation")
public class CreateReservationController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation createReservation(Customer customer)
	{
	
		Reservation reservation = new Reservation();
		try {
			ReservationDAO reserveDao = new ReservationDAO();
//			CustomerController custControl = new CustomerController();
//		    customer = custControl.create(customer);
			 
			 reservation = reserveDao.createReservation(customer);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reservation;
	}
}
