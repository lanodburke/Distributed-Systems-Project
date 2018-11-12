package ie.gmit.sw.WebService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	@Produces({MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN})
	@Path("/{value}")
	public Response getBooking(@PathParam("value") int value) throws RemoteException {
		
		this.databaseClient = new DatabaseClient();
		
		this.bookings = this.databaseClient.getBookings();
		
		Booking booking = null;
		for(Booking b : bookings) {
			System.out.println(b.toString());
			if(b.getBookingId() == value) {
				booking = b;
			}
		}
		
		if(booking == null) {
			String msg = "The requested order does not exist";
			return Response.status(404).entity(msg).build();  // return 404 for resource not found
		}
		else {
//			String msg = getBookingAsXML(booking);
			String msg = "Response reponse reponse.......";
			return Response.status(201).entity(msg).build();
		}		
	}
	
//	private String getBookingAsXML(Booking booking) {
//		StringWriter sw = new StringWriter();
//		Marshaller m;
//		try {
//			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.models");
//			m = jc.createMarshaller();
//			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//			m.marshal(booking, sw);
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}		
//		return sw.toString();
//	}
	
}

