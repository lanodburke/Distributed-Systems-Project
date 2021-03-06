package ie.gmit.sw.client;

import java.util.List;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import ie.gmit.sw.models.Booking;

public class BookingClient {

	private String url = "http://localhost:8080/WebService/webapi/booking/";

	public List<Booking> getAllBookings() {
        Client client = Client.create();
        WebResource webresource = client.resource(this.url);
                             
        return webresource.get(new GenericType<List<Booking>>(){});
    }
	
	public void createBooking(Booking booking) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(this.url);

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
		   .resource(this.url);

		ClientResponse response = webResource.type("application/xml")
		   .put(ClientResponse.class, booking);
		
		System.out.println("Response code: " + response.getStatus());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
	}
	
	public void deleteBooking(int bookingId) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(this.url + bookingId);

		ClientResponse response = webResource.delete(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
	}
	
}
