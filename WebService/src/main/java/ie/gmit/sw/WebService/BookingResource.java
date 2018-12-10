
package ie.gmit.sw.WebService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ie.gmit.sw.models.Booking;
import ie.gmit.sw.rmi.DatabaseClient;
import ie.gmit.sw.rmi.DatabaseService;

@Path("/booking")
public class BookingResource {
	
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	private DatabaseService databaseClient;
		
	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/")
	public Response getAllBookings() throws RemoteException {
		this.databaseClient = new DatabaseClient();
		
		this.bookings = this.databaseClient.getBookings();
		
		final GenericEntity<List<Booking>> entity = new GenericEntity<List<Booking>>(bookings) { };
		
		return Response.status(200).entity(entity).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML})
	@Produces({MediaType.TEXT_HTML})
	@Path("/")
	public Response createBooking(Booking newBooking) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		bookings.add(newBooking);
		this.databaseClient.createBooking(newBooking);
		String msg = "Booking created!";
		return Response.status(200).entity(msg).build(); // return 201 for resource created
		
	}

	@PUT
	@Consumes({MediaType.APPLICATION_XML})
	@Path("/")
	public Response updateBooking(Booking updatedBooking) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		System.out.println("In update booking " + updatedBooking.getBookingId());
		this.databaseClient.updateBooking(updatedBooking);
		String msg = "Booking updated!";
		return Response.status(200).entity(msg).build(); 
	}
	
	@DELETE
	@Produces({MediaType.TEXT_HTML})
	@Path("/{bookingId}")
	public Response deleteBooking(@PathParam("bookingId") int bookingId) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		this.databaseClient.deleteBooking(bookingId);
		
		String msg = "Customer deleted";
		return Response.status(200).entity(msg).build();
	}	
}

