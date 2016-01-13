package solutions.egen.vaidation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import solutions.egen.model.Table;
import solutions.egen.util.DBUtils;

public class TableValidation {

	
	public boolean validateId(int id) {
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM Seating Where ID=?");
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
