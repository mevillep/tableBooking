package solutions.egen.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import solutions.egen.dao.TableDAO;
import solutions.egen.exception.AppException;
import solutions.egen.model.Table;

@Path("/tableStatus")
public class TableStatusController {

	/*
	 * Owner can get to know what tables are on or off..
	 * He can log in from anywhere and know the status of the tables, or the business that he is making. How cool! 
	 * 
	 */
	@GET
	@Path("/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Table> checkStatus(@PathParam("status") String status){
		
		List<Table> tables= null;
		try {
			TableDAO dao = new TableDAO();
			
			tables = dao.checkStatus(status);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		
		return tables;
		
	} 


}
