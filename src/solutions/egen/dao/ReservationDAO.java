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
import solutions.egen.model.Reservation;
import solutions.egen.model.Table;
import solutions.egen.util.DBUtils;

public class ReservationDAO {

	public List<Reservation> findAll() throws AppException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT r.id, r.CustomerId, r.SeatingId, c.Name, c.Contact, c.PartySize, c.Date, c.Time, s.Size FROM Reservation r, Customer c, Seating s WHERE c.id = r.CustomerId and s.id = r.SeatingId");
			rs = ps.executeQuery();
			
			
			while(rs.next()){
				Reservation reservation = new Reservation();
			    reservation.setId(rs.getInt("id"));
			    reservation.setCustomerId(rs.getInt("CustomerId"));
			    reservation.setSeatingId(rs.getInt("SeatingId"));
			    reservation.setName(rs.getString("Name"));
			    reservation.setContact(rs.getString("Contact"));
			    reservation.setPartySize(rs.getInt("PartySize"));
			    reservation.setDate(rs.getDate("Date"));
			    reservation.setTime(rs.getTime("Time"));
			    reservation.setTableSize(rs.getInt("Size"));
				
				
		        reservations.add(reservation);
		      
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
		return reservations;
	}

	
	/*
	 * Owner can get information of a particular details. Can only find if seat is assigned.
	 * 
	 */
	public Reservation findOne(int id) throws AppException,NotFoundException {
		Reservation reservation = null;
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT r.id, c.Name, c.Contact, c.PartySize, c.Date, c.Time, s.Size FROM Reservation r, Customer c, Seating s WHERE c.id = r.CustomerId and s.id = r.SeatingId and r.id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			
			if(rs.next()){
				reservation = new Reservation();
				reservation.setId(rs.getInt("id"));
				reservation.setName(rs.getString("Name"));
				reservation.setContact(rs.getString("Contact"));
				reservation.setPartySize(rs.getInt("PartySize"));
				reservation.setDate(rs.getDate("Date"));
				reservation.setTime(rs.getTime("Time"));
				reservation.setTableSize(rs.getInt("Size"));
		
			}
			else{
				throw new NotFoundException("Requested Reservation Not Found");
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
		return reservation;
	}


//Owner can CREATE OR UPDATE customer, just like a Customer. by the  POST and PUT methods in CustomerDAO
	
	
	/*
	 * I am called from the autoAssign(). I give the correct table based on the PartySize. Only If the Table is free.
	 * The owner has to update the table to on or off for me to know, else, he can lose customers. 
	 * 
	 */
	public List<Table> automaticTable(Customer customer) throws AppException{

		List<Table> tables = new ArrayList<Table>();
		
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("Select id FROM Seating WHERE Size >= ? and Status = 'on'" );
			ps.setInt(1, customer.getPartySize());
			rs = ps.executeQuery();
			
			
			while(rs.next()){
				Table table = new Table();
			    table.setId(rs.getInt("id"));
			    
				
				
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

	/*
	 * THis inserts into the reservation table the customerId that it gets from customer. it calls other method to get the seatingId
	 * 
	 */
	public Reservation autoAssign(Customer customer) throws AppException {

		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reservation reservation = new Reservation();
		
          
		try {
			
			ps = con.prepareStatement("INSERT INTO Reservation(CustomerId, SeatingId )VALUES (?,?) ", PreparedStatement.RETURN_GENERATED_KEYS );
			ps.setInt(1, customer.getId());
			
			ps.setInt(2, automaticTable(customer).get(0).getId());  //this method returns the free table of the correct size
			//I could pass just the party size, not the whole customer.
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys() ;
			
			
			if(rs.next())
			{
				
				reservation.setId(rs.getInt(1));
//				reservation.setName(rs.getString("Name"));  /// no idea y i did this, but i get problem without this!
//				reservation.setContact(rs.getString("Contact"));
//				reservation.setPartySize(rs.getInt("PartySize"));
//				reservation.setDate(rs.getDate("Date"));
//				reservation.setTime(rs.getTime("Time"));
//				reservation.setTableSize(rs.getInt("Size"));
		    }

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
						
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
	
				return reservation;    
	}

	
	
	/*
	 * Status here means if teh reservation is done or not done. THe owner needs to be smart to update the status.
	 * He can also update the seatingId meaning update the table
	 * 
	 */
	public Reservation updateReservation(int id, Reservation reservation) throws AppException {
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
				try {
				
					ps = con.prepareStatement("UPDATE Reservation SET Status=?, SeatingId=?  WHERE id=?", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, reservation.getStatus());
					ps.setInt(2, reservation.getSeatingId());
				    ps.setInt(3, reservation.getId());
					
					ps.executeUpdate();
        		
        			
        			rs = ps.getGeneratedKeys() ;
					
					
					if(rs.next())
					{
						
						reservation.setId(rs.getInt("id"));
				    }
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new AppException(e.getMessage());
				}
				
	
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
		return reservation;
	
}



	public void delete(int id) throws AppException {
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
	
				
			   try {
				
					ps = con.prepareStatement("DELETE FROM Reservation WHERE id=?");
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



	public List<Reservation> checkStatus(String status) throws AppException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT r.id, c.Name, c.Contact, c.PartySize, c.Date, c.Time, s.Size, r.CustomerId, r.Status FROM Reservation r, Customer c, Seating s WHERE c.id = r.CustomerId and s.id = r.SeatingId and r.Status= ?");
			ps.setString(1, status);
			rs = ps.executeQuery();
			
			
			while(rs.next()){
				Reservation reservation = new Reservation();
			    reservation.setId(rs.getInt("id"));
			    reservation.setName(rs.getString("Name"));
			    reservation.setContact(rs.getString("Contact"));
			    reservation.setPartySize(rs.getInt("PartySize"));
			    reservation.setDate(rs.getDate("Date"));
			    reservation.setTime(rs.getTime("Time"));
			    reservation.setTableSize(rs.getInt("Size"));
			    reservation.setCustomerId(rs.getInt("CustomerId"));
			    reservation.setStatus(rs.getString("Status"));
				
				
		        reservations.add(reservation);
		      
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
		
		return reservations;
	}


	public Reservation createReservation(Customer customer) throws AppException {

		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reservation reservation = new Reservation();
		
		try {
			
			ps = con.prepareStatement("INSERT INTO Reservation(CustomerId )VALUES (?) ", PreparedStatement.RETURN_GENERATED_KEYS );
			ps.setInt(1, customer.getId());
			
			
			
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys() ;
			
			
			if(rs.next())
			{
				
				reservation.setId(rs.getInt(1));
				
		    }

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
						
		
		finally {
			DBUtils.closeResource(ps, rs, con);
		}
	
				return reservation;    
	}


	
}
