package solutions.egen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.NotFoundException;

import solutions.egen.exception.AppException;

import solutions.egen.model.Restaurant;
import solutions.egen.util.DBUtils;

public class RestaurantDAO {

	public Restaurant find() throws AppException {
		Restaurant restaurant = null;
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM Restaurant");
			rs = ps.executeQuery();
			
			
			if(rs.next()){
				restaurant = new Restaurant();
				restaurant.setId(rs.getInt("id"));
				restaurant.setName(rs.getString("Name"));
				restaurant.setContact(rs.getString("Contact"));
				restaurant.setEmail(rs.getString("Email"));
				restaurant.setAddress(rs.getString("Address"));
				restaurant.setDate(rs.getDate("Date"));
				restaurant.setStartTime(rs.getTime("StartTime"));
				restaurant.setCloseTime(rs.getTime("CloseTime"));
				
			}
			else{
				throw new NotFoundException("Something went Wrong");
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
		return restaurant;
	}

	
	
	public Restaurant update(int id, Restaurant restaurant) throws AppException {
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		
		      try {
				
					ps = con.prepareStatement("UPDATE Restaurant SET Name=?, Contact=?, Email=?, Address=?, Date=?, StartTime=?, CloseTime=? WHERE id=?", PreparedStatement.RETURN_GENERATED_KEYS);
					
					ps.setString(1, restaurant.getName());
					ps.setString(2, restaurant.getContact());
					ps.setString(3, restaurant.getEmail());
					ps.setString(4, restaurant.getAddress());
				    ps.setDate(5, restaurant.getDate());
					ps.setTime(6, restaurant.getStartTime());
					ps.setTime(7, restaurant.getCloseTime());
					ps.setInt(8, restaurant.getId());
					
        			ps.executeUpdate();
        		
        			
        			rs = ps.getGeneratedKeys() ;
					
					
					if(rs.next())
					{
						
						restaurant.setId(rs.getInt("id"));
				    }
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new AppException(e.getMessage());
				}
				
			
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
		return restaurant;
	
		}

	
	
}
