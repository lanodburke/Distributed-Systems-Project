package ie.gmit.sw.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ie.gmit.sw.models.Booking;

public interface DatabaseService extends Remote {
	public ArrayList<Booking> getBookings() throws RemoteException;
}
