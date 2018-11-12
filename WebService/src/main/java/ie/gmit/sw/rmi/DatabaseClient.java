package ie.gmit.sw.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ie.gmit.sw.models.Booking;

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
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		bookings = this.databaseService.getBookings();
		return bookings;
	}
	
}
