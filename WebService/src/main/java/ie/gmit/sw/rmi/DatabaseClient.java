package ie.gmit.sw.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ie.gmit.sw.models.Booking;
import ie.gmit.sw.models.Customer;
import ie.gmit.sw.models.Vehicle;

public class DatabaseClient implements DatabaseService {
	
	private DatabaseService databaseService;
	
	public DatabaseClient() throws RemoteException {
		super();
		try {
			this.databaseService = (DatabaseService) Naming.lookup("rmi://localhost:1099/databaseService");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Booking> getBookings() throws RemoteException {
		return databaseService.getBookings();
	}

	@Override
	public void createBooking(Booking booking) throws RemoteException {
		databaseService.createBooking(booking);
	}

	@Override
	public void deleteBooking(int bookingId) throws RemoteException {
		databaseService.deleteBooking(bookingId);
	}

	@Override
	public void updateBooking(Booking booking) throws RemoteException {
		databaseService.updateBooking(booking);
		
	}

	@Override
	public ArrayList<Customer> getCustomers() throws RemoteException {
		return databaseService.getCustomers();
	}

	@Override
	public void createCustomer(Customer customer) throws RemoteException {
		databaseService.createCustomer(customer);
	}

	@Override
	public void deleteCustomer(int customerId) throws RemoteException {
		databaseService.deleteCustomer(customerId);
	}

	@Override
	public void updateCustomer(Customer customer) throws RemoteException {
		databaseService.updateCustomer(customer);
	}

	@Override
	public ArrayList<Vehicle> getVehicles() throws RemoteException {
		return databaseService.getVehicles();
	}

	@Override
	public void createVehicle(Vehicle vehicle) throws RemoteException {
		databaseService.createVehicle(vehicle);
	}

	@Override
	public void deleteVehicle(int vehicleId) throws RemoteException {
		databaseService.deleteVehicle(vehicleId);
	}

	@Override
	public void updateVehicle(Vehicle vehicle) throws RemoteException {
		databaseService.updateVehicle(vehicle);
	}
	
}
