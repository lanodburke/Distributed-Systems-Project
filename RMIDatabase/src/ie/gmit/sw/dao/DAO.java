package ie.gmit.sw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ie.gmit.sw.models.Booking;
import ie.gmit.sw.models.Customer;
import ie.gmit.sw.models.Vehicle;

public class DAO {
	
	private Connection connection;
	
	public DAO() {
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookingdatabase?useSSL=false", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}	 
	}
	
	public ArrayList<Booking> getBookings() {
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		
		try {
			Statement statement = this.connection.createStatement();
			
			String selectString = "select booking_id, start_date, end_date, Customer.customer_id, Customer.f_name, \n" + 
	         		"Customer.s_name, Customer.address, Customer.dob, Vehicle.vehicle_id, Vehicle.registration, Vehicle.quality, Vehicle.mileage from Booking \n" + 
	         		"inner join Customer on Booking.customer_id = Customer.customer_id \n" + 
	         		"inner join Vehicle on Booking.vehicle_id = Vehicle.vehicle_id";
			
			 ResultSet resultSet = statement.executeQuery(selectString);
			 
	         while(resultSet.next()) { 
	        	// Booking
	            int bookingId = resultSet.getInt("booking_id");
	            Date startDate = resultSet.getDate("start_date");
	            Date endDate = resultSet.getDate("end_date");
	            
	            // Customer
	            int customerId = resultSet.getInt("customer_id");
	        	String firstName = resultSet.getString("f_name");
	            String surName = resultSet.getString("s_name");
	            String address = resultSet.getString("address");
	            Date dob = resultSet.getDate("dob");
	            
	            Customer customer = new Customer(customerId, firstName, surName, address, dob);
	            
	            // Vehicle
	            int vehicle_id = resultSet.getInt("vehicle_id");
	            String registration = resultSet.getString("registration");
	            String quality = resultSet.getString("quality");
	            int mileage = resultSet.getInt("mileage");
	            
	            Vehicle vehicle = new Vehicle(vehicle_id, registration, mileage, quality);
	            
	            Booking booking = new Booking(bookingId, customer, vehicle, startDate, endDate);
	            
	            // System.out.println(bookingId + " " + firstName + " " + surName + " " + registration + " " + quality + " " + startDate + " " + endDate);
	            
	            bookings.add(booking);
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookings;
	}
	
}
