package solutions.egen.vaidation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.NotFoundException;

import solutions.egen.exception.AppException;
import solutions.egen.model.Customer;
import solutions.egen.util.DBUtils;

public class CustomerValidation {

	public boolean validateContact(Customer customer) {
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		/*
		 * gives customers from Reservation and Customer table, if status is open
		 * 
		 * what is customer who is trying to register for 2nd time. Hence its imp to make status closed after reservation is complete.
		 */
		// SELECT * FROM Customer c, Reservation r Where c.id = r.CustomerId and r.Status = 'open' and c.Contact=?
		
		try {
			ps = con.prepareStatement("SELECT * FROM Customer c  Where c.Contact=?");
			ps.setString(1, customer.getContact());
			rs = ps.executeQuery();
			if(!rs.next()){
				return true;
			
			}
			
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
		return false;
	}

	public boolean validateId(int id) {
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM Customer Where ID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

		
			if(!rs.next()){
				return false;
			}
		
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
	
		return true;
	}


	
	
}
