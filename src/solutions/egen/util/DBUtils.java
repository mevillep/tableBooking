 package solutions.egen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
	
	private static final  String DB_URL = "jdbc:mysql://localhost:3306/Restaurant_db";
	private static final  String DB_USER = "root";
	private static final  String DB_PASSWORD = "password";
	
	static {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver has loaded.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error in loading MySQL JDBC Driver");
		}
	}
	
	
	public static Connection connect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("Connection Successful");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Connection NOT Successful");
		}
		
		return con;
	}

	
	public static void closeResource (PreparedStatement ps, ResultSet rs, Connection con) {
		try {
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}
			
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	
//	
//	public static void main(String[] args) {
//		DBUtils.connect();
//	}
}
