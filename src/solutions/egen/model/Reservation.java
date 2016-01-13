package solutions.egen.model;

import java.sql.Date;
import java.sql.Time;

public class Reservation extends Customer{

	private int id;
	private String status;
	private int customerId;
	private int seatingId;
	private int tableSize;
	
	
	
	
	
	/**
	 * @return the tableSize
	 */
	public int getTableSize() {
		return tableSize;
	}
	/**
	 * @param tableSize the tableSize to set
	 */
	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}



	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the seatingId
	 */
	public int getSeatingId() {
		return seatingId;
	}
	/**
	 * @param seatingId the seatingId to set
	 */
	public void setSeatingId(int seatingId) {
		this.seatingId = seatingId;
	}

	
}
