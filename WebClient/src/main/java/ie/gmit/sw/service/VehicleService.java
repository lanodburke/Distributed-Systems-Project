package ie.gmit.sw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import ie.gmit.sw.models.Vehicle;

@Service
public class VehicleService {
	
	private String url = "http://localhost:8080/WebService/webapi/vehicle/";

	public List<Vehicle> getAllVehicles() {
        Client client = Client.create();
        WebResource webresource = client.resource(this.url);
                             
        return webresource.get(new GenericType<List<Vehicle>>(){});
    }
	
	public void createVehicle(Vehicle vehicle) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(this.url);

		ClientResponse response = webResource.type("application/xml")
		   .post(ClientResponse.class, vehicle);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
	}
	
	public void updateVehicle(Vehicle vehicle) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(this.url + vehicle.getVehicleId());

		ClientResponse response = webResource.type("application/xml")
		   .put(ClientResponse.class, vehicle);
		
		System.out.println("Response code: " + response.getStatus());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
	}
	
	public void deleteVehicle(Vehicle vehicle) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(this.url + vehicle.getVehicleId());

		ClientResponse response = webResource.delete(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
	}
	
}
