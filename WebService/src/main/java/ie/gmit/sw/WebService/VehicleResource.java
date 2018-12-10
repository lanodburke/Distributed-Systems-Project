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

import ie.gmit.sw.models.Vehicle;
import ie.gmit.sw.rmi.DatabaseClient;
import ie.gmit.sw.rmi.DatabaseService;

@Path("/vehicle")
public class VehicleResource {
	
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();
	private DatabaseService databaseClient;
	
	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/")
	public Response getAllCustomers() throws RemoteException {
		this.databaseClient = new DatabaseClient();
		
		this.vehicles = this.databaseClient.getVehicles();
		
		final GenericEntity<List<Vehicle>> entity = new GenericEntity<List<Vehicle>>(vehicles) { };
		
		return Response.status(200).entity(entity).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML})
	@Path("/")
	public Response createVehicle(Vehicle newVehicle) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		vehicles.add(newVehicle);
		this.databaseClient.createVehicle(newVehicle);
		String msg = "vehicle created!";
		return Response.status(200).entity(msg).build(); // return 201 for resource created
	}

	@PUT
	@Consumes({MediaType.APPLICATION_XML})
	@Path("/")
	public Response updateVehicle(Vehicle updatedVehicle) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		this.databaseClient.updateVehicle(updatedVehicle);
		String msg = "Vehicle updated!";
		return Response.status(200).entity(msg).build(); 
	}
	
	@DELETE
	@Produces({MediaType.TEXT_HTML})
	@Path("/{vehicleId}")
	public Response deleteVehicle(@PathParam("vehicleId") int vehicleId) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		this.databaseClient.deleteVehicle(vehicleId);
		
		String msg = "vehicle deleted";
		return Response.status(200).entity(msg).build();
	}
}

