package solutions.egen.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import solutions.egen.dao.AutoAssignDAO;
import solutions.egen.dao.CustomerDAO;
import solutions.egen.dao.ReservationDAO;
import solutions.egen.dao.TableDAO;
import solutions.egen.exception.AppException;
import solutions.egen.model.Customer;
import solutions.egen.model.Reservation;
import solutions.egen.model.Table;
import solutions.egen.util.DBUtils;
import solutions.egen.vaidation.CustomerValidation;
//import solutions.egen.vaidation.ReservationValidation;
import solutions.egen.vaidation.ReservationValidation;

@Path("/reservations")
public class ReservationController {
	/*
	 * This is pertaining the reservation table
	 * The status field on the reservation table is to find if the reservation is open(customer yet came)
	 * or closed(customer finished dining or whatever)
	 */
	
	/*
	 * Owner can find a particular reservation
	 * 
	 */
    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation findOne(@PathParam("id") int id){
		
		Reservation reservation= null;
		try {
			ReservationDAO dao = new ReservationDAO();
			
			reservation = dao.findOne(id);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reservation;
	} 
        		
    /*
     * This is not correct. But I wanted to have /reservations/{status}open/closed
     * 
     */
//    @GET    
//   	@Path("/{status}")
//   	@Produces(MediaType.APPLICATION_JSON)
//   	public Reservation checkStatus(@PathParam("status") String status){
//   		
//   		Reservation reservation= null;
//   		try {
//   			ReservationDAO dao = new ReservationDAO();
//   			
//   			reservation = dao.checkStatus(status);
//   		} catch (AppException e) {
//   			// TODO Auto-generated catch block
//   			e.printStackTrace();
//   			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
//   		}
//   		
//   		
//   		return reservation;
//   		
//   	} 
    
    
    
    /*
     *Owner can get list of all teh reservations 
     * 
     */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reservation> findAll(){
		List<Reservation> reservations= null;
		try {
			ReservationDAO dao = new ReservationDAO();
			
			reservations = dao.findAll();
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		
		return reservations;
	}

	


	/*
	 * So, this method accepts a customer. and returns a reservation..
	 * 
	 */	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation autoAssign(Customer customer)
	{
	
		Reservation reservation = new Reservation();
		try {
			ReservationDAO reserveDao = new ReservationDAO();
			AutoAssignDAO autoDao = new AutoAssignDAO();
			
		
            CustomerValidation validate = new CustomerValidation();
			
			if(validate.validateContact(customer)){
			customer = autoDao.createCustomer(customer);
			}
			else{
				throw new AppException("Customer with same Contact already exists");
			}
			
			 reservation = reserveDao.autoAssign(customer);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reservation;
	}
	
	/*
	 * 
	 * 
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation update(@PathParam("id") int id, Reservation reservation){
	
		try {
			ReservationDAO dao = new ReservationDAO();
			CustomerValidation validate = new CustomerValidation();
			if(validate.validateId(id))
			{

				 reservation = dao.updateReservation(id, reservation);
			}
			else{
				throw new AppException("Reservation does not exist");
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
            return reservation;
	}
	
	/*
	 * Delete  an existing reservation
	 * 
	 */
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id){
		try {
			ReservationDAO dao = new ReservationDAO();
			ReservationValidation validate = new ReservationValidation();
			if(validate.validateId(id)){
				 dao.delete(id);
			}
			else{
				throw new AppException("Cannot Delete, Reservation does not exist");
			} 
			
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return Response.ok().build();
	
	}
	
} 
