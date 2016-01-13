package solutions.egen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import solutions.egen.exception.AppException;
import solutions.egen.model.Owner;
import solutions.egen.util.DBUtils;

public class OwnerDAO {

	
	
	public void authenticate(Owner owner) throws AppException {
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM Owner WHERE Email=? and Password=?");
			ps.setString(1, owner.getEmail());
			ps.setString(2, owner.getPassword());
			rs = ps.executeQuery();
			
			
			if(!rs.next()){
				
				throw new NotFoundException("Email or Password is incorrect");
		
			}
			else{
				//login successful
				System.out.println("Login successful");   // show owner homepage
				 // I want to generate a token here
			}
			 
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
