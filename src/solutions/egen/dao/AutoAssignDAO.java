package solutions.egen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import solutions.egen.exception.AppException;
import solutions.egen.model.Customer;
import solutions.egen.util.DBUtils;

public class AutoAssignDAO {

	
	/*
	 * Creates a Customer in the customer Table
	 * 
	 */
	public Customer createCustomer(Customer customer) throws AppException {

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


}
