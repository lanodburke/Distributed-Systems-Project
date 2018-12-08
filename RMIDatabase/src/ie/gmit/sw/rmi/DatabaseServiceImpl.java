package ie.gmit.sw.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import ie.gmit.sw.dao.DAO;
import ie.gmit.sw.models.Booking;
import ie.gmit.sw.models.Customer;
import ie.gmit.sw.models.Vehicle;

public class DatabaseServiceImpl extends UnicastRemoteObject implements DatabaseService{
	
	private static final long serialVersionUID = 1L;
	private DAO dao;

	public DatabaseServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public ArrayList<Booking> getBookings() throws RemoteException {
		this.dao = new DAO();
		ArrayList<Booking> bookings = dao.getBookings();
		
		System.out.println("Getting bookings....");
		
		return bookings;
	}

	@Override
	public void createBooking(Booking booking) throws RemoteException {
		this.dao = new DAO();
		dao.createBooking(booking);
	}

	@Override
	public void deleteBooking(int bookingId) throws RemoteException {
		this.dao = new DAO();
		dao.deleteBooking(bookingId);
	}

	@Override
	public void updateBooking(Booking booking) throws RemoteException {
		this.dao = new DAO();
		dao.updateBooking(booking);
	}

	@Override
	public ArrayList<Customer> getCustomers() throws RemoteException {
		this.dao = new DAO();
		ArrayList<Customer> customers = dao.getCustomers();
		return customers;
	}

	@Override
	public void createCustomer(Customer customer) throws RemoteException {
		this.dao = new DAO();
		dao.createCustomer(customer);
	}

	@Override
	public void deleteCustomer(int customerId) throws RemoteException {
		this.dao = new DAO();
		dao.deleteCustomer(customerId);
	}

	@Override
	public void updateCustomer(Customer customer) throws RemoteException {
		this.dao = new DAO();
		dao.updateCustomer(customer);
	}

	@Override
	public ArrayList<Vehicle> getVehicles() throws RemoteException {
		this.dao = new DAO();
		ArrayList<Vehicle> vehicles = dao.getVehicles();
		return vehicles;
	}

	@Override
	public void createVehicle(Vehicle vehicle) throws RemoteException {
		this.dao = new DAO();
		dao.createVehicle(vehicle);
	}

	@Override
	public void deleteVehicle(int vehicleId) throws RemoteException {
		this.dao = new DAO();
		dao.deleteVehicle(vehicleId);
	}

	@Override
	public void updateVehicle(Vehicle vehicle) throws RemoteException {
		this.dao = new DAO();
		dao.updateVehicle(vehicle);
	}
	
}
