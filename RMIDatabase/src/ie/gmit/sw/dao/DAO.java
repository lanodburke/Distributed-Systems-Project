package ie.gmit.sw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ie.gmit.sw.models.Booking;
import ie.gmit.sw.models.Customer;
import ie.gmit.sw.models.Vehicle;

public class DAO {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement p;
	
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
			this.statement = this.connection.createStatement();
			
			String selectString = "select booking_id, start_date, end_date, Customer.customer_id, Customer.f_name, \n" + 
	         		"Customer.s_name, Customer.address, Customer.dob, Vehicle.vehicle_id, Vehicle.registration, Vehicle.quality, Vehicle.mileage from Booking \n" + 
	         		"inner join Customer on Booking.customer_id = Customer.customer_id \n" + 
	         		"inner join Vehicle on Booking.vehicle_id = Vehicle.vehicle_id";
			
			 ResultSet resultSet = statement.executeQuery(selectString);
			 
			 
	         while(resultSet.next()) { 
	        	// Booking
	            int bookingId = resultSet.getInt("booking_id");
	            String startDate = resultSet.getString("start_date");
	            String endDate = resultSet.getString("end_date");
	            
	            // Customer
	            int customerId = resultSet.getInt("customer_id");
	        	String firstName = resultSet.getString("f_name");
	            String surName = resultSet.getString("s_name");
	            String address = resultSet.getString("address");
	            String dob = resultSet.getString("dob");
	            
	            Customer customer = new Customer(customerId, firstName, surName, address, dob);
	            
	            // Vehicle
	            int vehicle_id = resultSet.getInt("vehicle_id");
	            String registration = resultSet.getString("registration");
	            String quality = resultSet.getString("quality");
	            int mileage = resultSet.getInt("mileage");
	            
	            Vehicle vehicle = new Vehicle(vehicle_id, registration, mileage, quality);
	            
	            Booking booking = new Booking(bookingId, customer, vehicle, startDate, endDate);
	            
	            bookings.add(booking);
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookings;
	}
	
	public void createBooking(Booking booking) {
		try {
			this.statement = this.connection.createStatement();
			
			String insertString = "insert into Booking(booking_id, customer_id, vehicle_id, start_date, end_date) values(?, ?, ?, ?, ?)";
			
			p = connection.prepareStatement(insertString);
			p.setInt(1, booking.getBookingId());
			p.setInt(2, booking.getCustomer().getCustomerId());
			p.setInt(3, booking.getVehicle().getVehicleId());
			p.setString(4, booking.getBookingStartDate());
			p.setString(5, booking.getBookingEndDate());

			p.execute();
			
			System.out.println("Inserting booking!");
			
			p.close();
	    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBooking(int bookingId) {
		try {
			this.statement = this.connection.createStatement();
			
			String deleteString = "delete from Booking where booking_id = ?";
			
			p = connection.prepareStatement(deleteString);
			
			p.setInt(1, bookingId);

			p.execute();
			
			System.out.println("Deleting booking!");
			
			p.close();
	    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBooking(Booking booking) {
		try {
			this.statement = this.connection.createStatement();
			
			String updateString = "update Booking set customer_id = ?, vehicle_id = ?, start_date = ?, end_date = ? where booking_id = ?";
			
			p = connection.prepareStatement(updateString);
			
			p.setInt(1, booking.getCustomer().getCustomerId());
			p.setInt(2, booking.getVehicle().getVehicleId());
			p.setString(3, booking.getBookingStartDate());
			p.setString(4, booking.getBookingEndDate());
			p.setInt(5, booking.getBookingId());

			p.execute();
			
			System.out.println("Updating booking!");
			
			p.close();
	    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Customer> getCustomers() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		try {
			this.statement = this.connection.createStatement();
			
			String selectString = "select * from Customer";
			
			 ResultSet resultSet = statement.executeQuery(selectString);
			 
	         while(resultSet.next()) { 	            
	            // Customer
	            int customerId = resultSet.getInt("customer_id");
	        	String firstName = resultSet.getString("f_name");
	            String surName = resultSet.getString("s_name");
	            String address = resultSet.getString("address");
	            String dob = resultSet.getString("dob");
	            
	            Customer customer = new Customer(customerId, firstName, surName, address, dob);
	           
	            
	            customers.add(customer);
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	public void createCustomer(Customer customer) {
		try {
			this.statement = this.connection.createStatement();
			
			String insertString = "insert into Customer(customer_id, f_name, s_name, address, dob) values(?, ?, ?, ?, ?)";
			
			p = connection.prepareStatement(insertString);
			p.setInt(1, customer.getCustomerId());
			p.setString(2, customer.getFirstName());
			p.setString(3, customer.getLastName());
			p.setString(4, customer.getAddress());
			p.setString(5, customer.getDob());

			p.execute();
			
			System.out.println("Inserting customer!");
			
			p.close();
	    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCustomer(int customerId) {
		try {
			this.statement = this.connection.createStatement();
			
			String deleteString = "delete from Customer where customer_id = ?";
			
			p = connection.prepareStatement(deleteString);
			
			p.setInt(1, customerId);

			p.execute();
			
			System.out.println("Deleting customer!");
			
			p.close();
	    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCustomer(Customer customer) {
		try {
			this.statement = this.connection.createStatement();
			
			String updateString = "update Customer set f_name = ?, s_name = ?, address = ?, dob = ? where customer_id = ?";
			
			p = connection.prepareStatement(updateString);
			
			p.setString(1, customer.getFirstName());
			p.setString(2, customer.getLastName());
			p.setString(3, customer.getAddress());
			p.setString(4, customer.getDob());
			p.setInt(5, customer.getCustomerId());

			p.execute();
			
			System.out.println("Updating customer!");
			
			p.close();
	    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Vehicle> getVehicles() {
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		try {
			this.statement = this.connection.createStatement();
			
			String selectString = "select * from Vehicle";
			
			 ResultSet resultSet = statement.executeQuery(selectString);
			 
	         while(resultSet.next()) { 	            
	            // Vehicle
	            int vehicle_id = resultSet.getInt("vehicle_id");
	            String registration = resultSet.getString("registration");
	            String quality = resultSet.getString("quality");
	            int mileage = resultSet.getInt("mileage");
	            
	            Vehicle vehicle = new Vehicle(vehicle_id, registration, mileage, quality);
	           
	            
	            vehicles.add(vehicle);
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicles;
	}
	
	public void createVehicle(Vehicle vehicle) {
		try {
			this.statement = this.connection.createStatement();
			
			String insertString = "insert into Vehicle(vehicle_id, registration, quality, mileage) values(?, ?, ?, ?)";
			
			p = connection.prepareStatement(insertString);
			p.setInt(1, vehicle.getVehicleId());
			p.setString(2, vehicle.getRegistrationNumber());
			p.setString(3, vehicle.getCondition());
			p.setInt(4, vehicle.getMileage());

			p.execute();
			
			System.out.println("Inserting vehicle!");
			
			p.close();
	    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteVehicle(int vehicleId) {
		try {
			this.statement = this.connection.createStatement();
			
			String deleteString = "delete from Vehicle where vehicle_id = ?";
			
			p = connection.prepareStatement(deleteString);
			
			p.setInt(1, vehicleId);

			p.execute();
			
			System.out.println("Deleting vehicle!");
			
			p.close();
	    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateVehicle(Vehicle vehicle) {
		try {
			this.statement = this.connection.createStatement();
			
			String updateString = "update Vehicle set registration = ?, quality = ?, mileage = ? where vehicle_id = ?";
			
			p = connection.prepareStatement(updateString);
			
			p.setString(1, vehicle.getRegistrationNumber());
			p.setString(2, vehicle.getCondition());
			p.setInt(3, vehicle.getMileage());
			p.setInt(4, vehicle.getVehicleId());

			p.execute();
			
			System.out.println("Updating vehicle!");
			
			p.close();
	    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
