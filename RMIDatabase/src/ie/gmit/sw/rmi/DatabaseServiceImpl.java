package ie.gmit.sw.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import ie.gmit.sw.dao.DAO;
import ie.gmit.sw.models.Booking;

public class DatabaseServiceImpl extends UnicastRemoteObject implements DatabaseService{
	
	private static final long serialVersionUID = 1L;

	public DatabaseServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public ArrayList<Booking> getBookings() throws RemoteException {
		DAO dao = new DAO();
		ArrayList<Booking> bookings = dao.getBookings();
		
		System.out.println("Getting bookings....");
		
		return bookings;
	}
	
	
	
}
