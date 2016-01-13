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

import solutions.egen.dao.CustomerDAO;
import solutions.egen.exception.AppException;
import solutions.egen.model.Customer;
import solutions.egen.util.DBUtils;
import solutions.egen.vaidation.CustomerValidation;

@Path("/customers")
public class CustomerController {

	
/*
 *   Owner can find a single customer Detail
 * 
 */
    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer findOne(@PathParam("id") int id){
		
		Customer customer= null;
		try {
			CustomerDAO dao = new CustomerDAO();
			
			customer = dao.findOne(id);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		
		return customer;
		
	} 
	
    
    /*
     * Owner can Find all the customer that have registered
     * 
     */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> findAll(){
		//System.out.println("i am in customers");
		List<Customer> customers= null;
		try {
			CustomerDAO dao = new CustomerDAO();
			
			customers = dao.findAll();
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		
		return customers;
	}


	/*
	 * Customer Or Owner can create a reservation
	 * 
	 * This method returns an ID, I will give that to the customer as his customer confirmation code
	 * THe customer could go ahead and delete or update his reservation details based on the confirmation code
	 * Unique and efficient way to do, I think!
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int create(Customer customer)
	{
		
		try {
			
			CustomerDAO dao = new CustomerDAO();
			CustomerValidation validate = new CustomerValidation();
			
			if(validate.validateContact(customer)){
			customer = dao.create(customer);
			}
			else{
				throw new AppException("Customer with same Contact already exists");
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return customer.getId();   //confirmation code
		//return customer;
	}
	
	
	/*
	 * The customer can update id details of reservation by giving his ID, Or the confirmation code as given to him
	 * 
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Customer update(@PathParam("id") int id, Customer customer){
	
		try {
			CustomerDAO dao = new CustomerDAO();
			CustomerValidation validate = new CustomerValidation();
			if(validate.validateId(id))
			{

				 customer = dao.update(id, customer);
			}
			else{
				throw new AppException("Customer does not exist");
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		

		return customer;
	}
	
	
	/*
	 * A customer or Owner can Delete an existing Reservation. 
	 * 
	 * THe Delete takes customer Id, that is his Confirmation Code!
	 * 
	 */
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id){
		try {
			CustomerDAO dao = new CustomerDAO();
			CustomerValidation validate = new CustomerValidation();
			if(validate.validateId(id)){
				 dao.delete(id);
			}
			else{
				throw new AppException("Cannot Delete, Customer does not exist");
			}
			
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return Response.ok().build();
	
	}
	
} 
