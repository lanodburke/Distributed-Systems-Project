package ie.gmit.sw.models;

import java.util.Date;

public class Booking {
	private Customer customer;
	private Vehicle vehicle;
	private Date bookingStartDate;
	private Date bookingEndDate;
	private int bookingId;
	
	public Booking(int bookingId, Customer customer, Vehicle vehicle, Date bookingStartDate, Date bookingEndDate) {
		super();
		this.customer = customer;
		this.vehicle = vehicle;
		this.bookingStartDate = bookingStartDate;
		this.bookingEndDate = bookingEndDate;
		this.bookingId = bookingId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Date getBookingStartDate() {
		return bookingStartDate;
	}

	public void setBookingStartDate(Date bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}

	public Date getBookingEndDate() {
		return bookingEndDate;
	}

	public void setBookingEndDate(Date bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	@Override
	public String toString() {
		return "Booking [customer=" + customer + ", vehicle=" + vehicle + ", bookingStartDate=" + bookingStartDate
				+ ", bookingEndDate=" + bookingEndDate + ", bookingId=" + bookingId + "]";
	}
	
}
