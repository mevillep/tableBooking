package solutions.egen.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import solutions.egen.dao.CustomerDAO;
import solutions.egen.dao.OwnerDAO;
import solutions.egen.exception.AppException;
import solutions.egen.model.Customer;
import solutions.egen.model.Owner;


@Path("/owner")
public class OwnerController {
	
	

	/*
	 * This is the authentication of the Owner. I would have loved to create a Token and give it to the Owner.
	 * 
	 * 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Authenticate(Owner owner)
	{
		
		try {
			OwnerDAO dao = new OwnerDAO();
			 // Boolean duplicate = dao.checkDuplicate(customer);
				dao.authenticate(owner);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
  		
		return Response.ok().build();
	}

}
