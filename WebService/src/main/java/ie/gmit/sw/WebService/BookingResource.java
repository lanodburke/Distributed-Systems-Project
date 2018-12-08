package ie.gmit.sw.WebService;

import java.io.StringReader;
import java.io.StringWriter;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import ie.gmit.sw.models.Booking;
import ie.gmit.sw.rmi.DatabaseClient;
import ie.gmit.sw.rmi.DatabaseService;

@Path("/booking")
public class BookingResource {
	
	private List<Booking> bookings = new ArrayList<Booking>();
	private DatabaseService databaseClient;
		
	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/{value}")
	public Response getBooking(@PathParam("value") int value) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		
		this.bookings = this.databaseClient.getBookings();
		
		Booking booking = null;
		for(Booking b : bookings) {
			if(b.getBookingId() == value) {
				booking = b;
			}
		}
		
		if(booking == null) {
			String msg = "The requested order does not exist";
			return Response.status(404).entity(msg).build();  // return 404 for resource not found
		}
		else {
			String msg = getBookingAsXML(booking);
			return Response.status(200).entity(msg).build();
		}		
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML})
	@Path("/createBooking/{bookingId}")
	public Response createBooking(@PathParam("bookingId") int bookingId, String bookingXML) throws RemoteException {
		
		Booking booking = null;
		this.databaseClient = new DatabaseClient();
		for(Booking b : bookings) {
			if(b.getBookingId() == bookingId) {
				booking = b;
			}
		}
		
		if(booking != null) {
			String msg = "The order number " + bookingId + " already exists";
			return Response.status(409).entity(msg).build();
		}
		else {
			Booking newBooking = getBookingFromXml(bookingXML);
			bookings.add(newBooking);
			this.databaseClient.createBooking(newBooking);
			String msg = "Booking created!";
			return Response.status(200).entity(msg).build(); // return 201 for resource created
		}
	}

	@PUT
	@Consumes({MediaType.APPLICATION_XML})
	@Path("/updateBooking/{bookingId}")
	public Response updateBooking(@PathParam("bookingId") int bookingId, String bookingXML) throws RemoteException {
		Booking booking = null;
		this.databaseClient = new DatabaseClient();
		for(Booking b : bookings) {
			if(b.getBookingId() == bookingId) {
				booking = b;
			}
		}
		
		if(booking != null) {
			String msg = "The order number " + bookingId + " already exists";
			return Response.status(409).entity(msg).build();
		}
		else {
			Booking updatedBooking = getBookingFromXml(bookingXML);
			this.databaseClient.updateBooking(updatedBooking);
			String msg = "Booking updated!";
			return Response.status(200).entity(msg).build(); 
		}
	}
	
	@DELETE
	@Produces({MediaType.TEXT_HTML})
	@Path("/deleteBooking/{bookingId}")
	public Response deleteBooking(@PathParam("bookingId") int bookingId) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		this.databaseClient.deleteBooking(bookingId);
		
		String msg = "Customer deleted";
		return Response.status(200).entity(msg).build();
	}
	
	private String getBookingAsXML(Booking bo) {
		StringWriter sw = new StringWriter();
		Marshaller m;
		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.models");
			m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(bo, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}		
		return sw.toString();
	}
	
	private Booking getBookingFromXml(String input) {
		StringReader sr1 = new StringReader(input);
		Unmarshaller um1;
		Booking boFromXml = null;
		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.models");
			um1 = jc.createUnmarshaller();
			StreamSource source1 = new StreamSource(sr1);
			JAXBElement<Booking> boElement1 = um1.unmarshal(source1, Booking.class);
			boFromXml = (Booking) boElement1.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return boFromXml;
		
	}
	
}

