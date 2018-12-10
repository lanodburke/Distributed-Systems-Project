package ie.gmit.sw.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ie.gmit.sw.models.Booking;
import ie.gmit.sw.models.Customer;
import ie.gmit.sw.models.Vehicle;

public class Runner {
	public static void main(String[] args) {
		
		Scanner console = new Scanner(System.in);
		
		BookingClient bookingClient = new BookingClient();
		CustomerClient customerClient = new CustomerClient();
		VehicleClient vehicleClient = new VehicleClient();
		
		List<Booking> bookings = new ArrayList<Booking>();
		List<Customer> customers = new ArrayList<Customer>();
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		int option;
	
		
		do {
			printMenuOne();

			option = console.nextInt();
			switch(option){
			case 1:
				bookings = bookingClient.getAllBookings();
				 System.out.printf("\n%-20s %-20s %-20s %-20s %-20s", "Booking Id", "Customer Name", 
				            "Vehicle Registration", "Booking Start Date", "Booking End Date");
				for (Booking booking : bookings) {
					 System.out.printf("\n%-20d %-20s %-20s %-20s %-20s", booking.getBookingId(), 
							 booking.getCustomer().getFirstName() + " " + booking.getCustomer().getLastName(), 
					            booking.getVehicle().getRegistrationNumber(), booking.getBookingStartDate(), booking.getBookingEndDate());
				}
				System.out.println("");
				break;
			case 2:
				customers = customerClient.getAllCustomers();
				 System.out.printf("\n%-20s %-20s %-20s %-40s %-20s", "Customer Id", "First Name", 
				            "Last Name", "Address", "DOB");
				for (Customer customer : customers) {
					 System.out.printf("\n%-20d %-20s %-20s %-40s %-20s", customer.getCustomerId(),customer.getFirstName(),
							 customer.getLastName(), customer.getAddress(), customer.getDob());
				}
				System.out.println("");
				break;
			case 3:
				 vehicles = vehicleClient.getAllVehicles();
				 System.out.printf("\n%-20s %-20s %-20s %-20s", "Vehicle Id", "Registration Number", 
				            "Mileage", "Condition");
				for (Vehicle vehicle : vehicles) {
					 System.out.printf("\n%-20d %-20s %-20d %-20s", vehicle.getVehicleId(), vehicle.getRegistrationNumber(),
							 vehicle.getMileage(), vehicle.getCondition());
				}
				System.out.println("");
				break;
			case 4:
				System.out.println("Enter the Vehicle Id: ");
				int vehicleId = console.nextInt();
				
				System.out.println("Enter the Customer Id: ");
				int customerId = console.nextInt();
				
				System.out.println("Enter the Booking Start Date: ");
				String startDate = console.next();
				
				System.out.println("Enter the Booking End Date: ");
				String endDate = console.next();
				
				Customer customer = new Customer();
				customer.setCustomerId(customerId);
				
				Vehicle vehicle = new Vehicle();
				vehicle.setVehicleId(vehicleId);
				
				Booking newBooking = new Booking(customer, vehicle, 0, startDate, endDate);
				bookingClient.createBooking(newBooking);
				System.out.println("Created new Booking!");
				break;
			case 5:
				System.out.println("Enter the Booking Id to update:");
				int bookingId = console.nextInt();
				
				System.out.println("Enter the Vehicle Id:");
				int updatedVehicleId = console.nextInt();
				
				System.out.println("Enter the Customer Id:");
				int updatedCustomerId = console.nextInt();
				
				System.out.println("Enter the Booking Start Date:");
				String updatedStartDate = console.next();
				
				System.out.println("Enter the Booking End Date:");
				String updatedEndDate = console.next();
				
				Customer updatedCustomer = new Customer();
				updatedCustomer.setCustomerId(updatedCustomerId);
				
				Vehicle updatedVehicle = new Vehicle();
				updatedVehicle.setVehicleId(updatedVehicleId);
				
				Booking updatedBooking = new Booking(updatedCustomer, updatedVehicle, bookingId, updatedStartDate, updatedEndDate);
				
				bookingClient.updateBooking(updatedBooking);
				
				System.out.println("Updated Booking!");
				
				break;
			case 6:
				System.out.println("Enter the Booking Id to delete:");
				int deletedBookingId = console.nextInt();
				bookingClient.deleteBooking(deletedBookingId);
				
				System.out.println("Deleting Booking!");
				
				break;
			case 7:
				break;
			default:
				System.out.println("Please enter an option from 1 - 13");
			}

		}while(option != 13);
		console.close();
		
	}
	
	public static void printMenuOne(){
		System.out.println(" ________________________________________________");
		System.out.println("|               Booking Client                   |");
		System.out.println("|________________________________________________|");
		System.out.println(" 1) View Bookings:");
		System.out.println(" 2) View Customers");
		System.out.println(" 3) View Vehicles");
		System.out.println(" 4) Add Booking");
		System.out.println(" 5) Update Booking");
		System.out.println(" 6) Delete Booking");
		System.out.println(" 7) Exit");
	}
	
}
