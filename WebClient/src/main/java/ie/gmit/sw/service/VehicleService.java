package ie.gmit.sw.service;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import ie.gmit.sw.models.Vehicle;

@Service
public class VehicleService {
	
	private String url = "http://localhost:8080/WebService/webapi/vehicle/";

	public List<Vehicle> getAllVehicles() {
        Client client = Client.create();
        WebResource webresource = client.resource(this.url + "vehicles/");
                             
        return webresource.get(new GenericType<List<Vehicle>>(){});
    }
	
	public void createVehicle(Vehicle vehicle) {
        Client client = Client.create();        
        WebResource webResource = client.resource(this.url + "createVehicle/" + vehicle.getVehicleId());
        webResource.type(MediaType.APPLICATION_XML)
          .accept(MediaType.APPLICATION_XML)
          .post(Vehicle.class, vehicle);
        return;
    }
	
}
