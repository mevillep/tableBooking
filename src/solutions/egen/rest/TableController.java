
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


    import solutions.egen.dao.TableDAO;
    import solutions.egen.exception.AppException;

    import solutions.egen.model.Table;
    import solutions.egen.vaidation.TableValidation;

	@Path("/tables")
	public class TableController {
		
		
		/*
		 *
		 * Owner can CRUD the table(Seating table)..Plain old.
		 * 
		 */
		
		
	    @GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Table findOne(@PathParam("id") int id){
			
	    	Table table= null;
			try {
				TableDAO dao = new TableDAO();
				
				table = dao.findOne(id);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			
			
			return table;
			
		} 
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Table> findAll(){
			//System.out.println("i am in tables");
			List<Table> tables= null;
			try {
				TableDAO dao = new TableDAO();
				
				tables = dao.findAll();
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			
			
			return tables;
		}


		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Table create(Table table)
		{
			
			try {
				
				TableDAO dao = new TableDAO();
				table = dao.create(table);
			
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			return table;
		}
		
		@PUT
		@Path("/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Table update(@PathParam("id") int id, Table table){
		
			try {
				TableDAO dao = new TableDAO();
				TableValidation validate = new TableValidation();
				if(validate.validateId(id))
				{

					 table = dao.update(id, table);
				}
				else{
					throw new AppException("Table does not exist");
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			

			return table;
		}
		
		
		
		@DELETE
		@Path("/{id}")
		public Response delete(@PathParam("id") int id){
			try {
				TableDAO dao = new TableDAO();
				TableValidation validate = new TableValidation();
				if(validate.validateId(id)){
					 dao.delete(id);
				}
				else{
					throw new AppException("Cannot Delete, Table does not exist");
				}
				
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			return Response.ok().build();
		
		}
		
	} 

