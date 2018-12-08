package ie.gmit.sw.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ie.gmit.sw.models.Booking;
import ie.gmit.sw.models.Customer;
import ie.gmit.sw.models.Vehicle;

public interface DatabaseService extends Remote {
	public ArrayList<Booking> getBookings() throws RemoteException;
	public void createBooking(Booking booking) throws RemoteException;
	public void deleteBooking(int bookingId) throws RemoteException;
	public void updateBooking(Booking booking) throws RemoteException;
	
	public ArrayList<Customer> getCustomers() throws RemoteException;
	public void createCustomer(Customer customer) throws RemoteException;
	public void deleteCustomer(int customerId) throws RemoteException;
	public void updateCustomer(Customer customer) throws RemoteException;
	
	public ArrayList<Vehicle> getVehicles() throws RemoteException;
	public void createVehicle(Vehicle vehicle) throws RemoteException;
	public void deleteVehicle(int vehicleId) throws RemoteException;
	public void updateVehicle(Vehicle vehicle) throws RemoteException;
}
