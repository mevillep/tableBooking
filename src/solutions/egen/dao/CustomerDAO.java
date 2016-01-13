package solutions.egen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import solutions.egen.exception.AppException;
import solutions.egen.model.Customer;
import solutions.egen.model.Owner;
import solutions.egen.util.DBUtils;

public class CustomerDAO {

	
	/*
	 * Finds all the customers from the customer database. This includes all the past and future customers.. 
	 * All of them
	 * 
	 */
	public List<Customer> findAll() throws AppException {
		List<Customer> customers = new ArrayList<Customer>();
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM Customer");
			rs = ps.executeQuery();
		
			
			while(rs.next()){
				Customer customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("Name"));
				customer.setContact(rs.getString("Contact"));
				customer.setPartySize(rs.getInt("PartySize"));
				customer.setDate(rs.getDate("Date"));
				customer.setTime(rs.getTime("Time"));
				customer.setMessage(rs.getString("Message"));
		        customers.add(customer);
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		return customers;
	}

	
	/*
	 *  Selects from customer DB for the required ID, and passes back the customer 
	 * 
	 */
	public Customer findOne(int id) throws AppException,NotFoundException {
		Customer customer = null;
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM Customer Where ID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			
			if(rs.next()){
				customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("Name"));
				customer.setContact(rs.getString("Contact"));
				customer.setPartySize(rs.getInt("PartySize"));
				customer.setDate(rs.getDate("Date"));
				customer.setTime(rs.getTime("Time"));
				customer.setMessage(rs.getString("Message"));
		
			}
			else{
				throw new NotFoundException("Requested Customer Not Found");
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
		return customer;
	}



	/*
	 * Creates a Customer in the customer Table
	 * 
	 */
	public Customer create(Customer customer) throws AppException {

		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
						try {
						
							ps = con.prepareStatement("INSERT INTO Customer(Name, Contact, PartySize, Date, Time, Message) VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS );
							ps.setString(1, customer.getName());
							ps.setString(2, customer.getContact());
							ps.setInt(3, customer.getPartySize());
							ps.setDate(4, customer.getDate());
							ps.setTime(5, customer.getTime());
							ps.setString(6, customer.getMessage());
							
							ps.executeUpdate();
							
							rs = ps.getGeneratedKeys() ;
							
							
							if(rs.next())
							{
								
								customer.setId(rs.getInt(1));
						    }

							
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new AppException(e.getMessage());
						}
				
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
	

				return customer;
	}

	
	
	/*
	 * Plain update to the customer table
	 * 
	 */
	public Customer update(int id, Customer customer) throws AppException {
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
				try {
				
					ps = con.prepareStatement("UPDATE Customer SET Name=?, Contact=?, PartySize=?, Date=?, Time=?, Message=? WHERE id=?", PreparedStatement.RETURN_GENERATED_KEYS);
					
					ps.setString(1, customer.getName());
					ps.setString(2, customer.getContact());
					ps.setInt(3, customer.getPartySize());
					ps.setDate(4, customer.getDate());
					ps.setTime(5, customer.getTime());
					ps.setString(6, customer.getMessage());
					ps.setInt(7, customer.getId());
					
        			ps.executeUpdate();
        		
        			
        			rs = ps.getGeneratedKeys() ;
					
					
					if(rs.next())
					{
						
						customer.setId(rs.getInt("id"));
				    }
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new AppException(e.getMessage());
				}
				
				
			
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
		return customer;
	
		

	}



	public void delete(int id) throws AppException {
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
	
				
			   try {
				
					ps = con.prepareStatement("DELETE FROM Customer WHERE id=?");
					ps.setInt(1,id);
				    ps.executeUpdate();
        		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new AppException(e.getMessage());
				}
				
				
		
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
	}






}
