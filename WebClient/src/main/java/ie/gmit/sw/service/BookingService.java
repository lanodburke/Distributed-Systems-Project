package ie.gmit.sw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import ie.gmit.sw.models.Booking;

@Service
public class BookingService {
	
	private String url = "http://localhost:8080/WebService/webapi/booking/";

	public List<Booking> getAllBookings() {
        Client client = Client.create();
        WebResource webresource = client.resource(this.url + "bookings/");
                             
        return webresource.get(new GenericType<List<Booking>>(){});
    }
	
	public void createBooking(Booking booking) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(this.url + "createBooking/");

		ClientResponse response = webResource.type("application/xml")
		   .post(ClientResponse.class, booking);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
	}
	
	public void updateBooking(Booking booking) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(this.url + "updateBooking/" + booking.getBookingId());

		ClientResponse response = webResource.type("application/xml")
		   .put(ClientResponse.class, booking);
		
		System.out.println("Response code: " + response.getStatus());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
	}
	
	public void deleteBooking(Booking booking) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(this.url + "deleteBooking/" + booking.getBookingId());

		ClientResponse response = webResource.delete(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
	}
	
}
