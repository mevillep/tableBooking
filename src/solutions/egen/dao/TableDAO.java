package solutions.egen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import solutions.egen.exception.AppException;
import solutions.egen.model.Reservation;
import solutions.egen.model.Table;
import solutions.egen.util.DBUtils;

public class TableDAO {

	public List<Table> findAll() throws AppException {
		List<Table> tables = new ArrayList<Table>();
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM Seating");
			rs = ps.executeQuery();
			
			
			while(rs.next()){
				Table table = new Table();
				table.setId(rs.getInt("id"));
				table.setSize(rs.getInt("Size"));
				table.setType(rs.getString("Type"));
		        tables.add(table);
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		return tables;
	}

	
	
	public Table findOne(int id) throws AppException,NotFoundException {
		Table table = null;
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM Seating Where ID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			
			if(rs.next()){
				table = new Table();
				table.setId(rs.getInt("id"));
				table.setSize(rs.getInt("Size"));
				table.setType(rs.getString("Type"));
				
		
			}
			else{
				throw new NotFoundException("Requested Seating Not Found");
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
		return table;
	}




	public Table create(Table table) throws AppException {

		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
						try {
						
							ps = con.prepareStatement("INSERT INTO Seating(Size, Type) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS );
							ps.setInt(1, table.getSize());
							ps.setString(2, table.getType());
							
							
							ps.executeUpdate();
							
							rs = ps.getGeneratedKeys() ;
							
							
							if(rs.next())
							{
								
								table.setId(rs.getInt(1));
						    }

							
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new AppException(e.getMessage());
						}
				
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
	

				return table;
	}

	
	
	
	public Table update(int id, Table table) throws AppException {
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
				try {
				
					ps = con.prepareStatement("UPDATE Seating SET Size=?, Type=? WHERE id=?", PreparedStatement.RETURN_GENERATED_KEYS);
					
					ps.setInt(1, table.getSize());
					ps.setString(2, table.getType());
					ps.setInt(3, table.getId());
					
        			ps.executeUpdate();
        		
        			
        			rs = ps.getGeneratedKeys() ;
					
					
					if(rs.next())
					{
						
						table.setId(rs.getInt("id"));
				    }
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new AppException(e.getMessage());
				}
				
				
			
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
		return table;
	
		

	}



	public void delete(int id) throws AppException {
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
	
				
			   try {
				
					ps = con.prepareStatement("DELETE FROM Seating WHERE id=?");
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




	public List<Table> checkStatus(String status) throws AppException {
		List<Table> tables = new ArrayList<Table>();
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT t.id, t.Size, t.Type FROM Seating t WHERE Status= ?");
			ps.setString(1, status);
			rs = ps.executeQuery();
			
			
			while(rs.next()){
				Table table = new Table();
				table.setId(rs.getInt("id"));
			    table.setSize(rs.getInt("Size"));
			    table.setType(rs.getString("Type"));
			    
				
				
			    tables.add(table);
		      
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
		return tables;
	}





}
