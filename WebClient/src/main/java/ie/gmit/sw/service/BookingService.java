package ie.gmit.sw.service;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import ie.gmit.sw.models.Booking;

@Service
public class BookingService {
	
	private String url = "http://localhost:8080/WebService/webapi/booking/";

	public List<Booking> getAllBookings() {
        Client client = Client.create();
        WebResource webresource = client.resource("http://localhost:8080/WebService/webapi/booking/bookings");
                             
        return webresource.get(new GenericType<List<Booking>>(){});
    }
	
	public void createBooking(Booking booking) {
        Client client = Client.create();        
        WebResource webResource = client.resource(this.url + "createBooking/" + booking.getBookingId());
        webResource.type(MediaType.APPLICATION_XML)
          .accept(MediaType.APPLICATION_XML)
          .post(Booking.class, booking);
        return;
    }
}
