package solutions.egen.vaidation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.NotFoundException;

import solutions.egen.exception.AppException;
import solutions.egen.model.Reservation;
import solutions.egen.util.DBUtils;

public class ReservationValidation {

	public boolean validateContact(Reservation reservation) {
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM Reservation r, Customer c Where c.id = r.CustomerId and r.Status = 'open' and c.Contact=?");
			ps.setString(1, reservation.getContact());
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
			ps = con.prepareStatement("SELECT * FROM Reservation Where ID=?");
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
