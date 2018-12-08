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

import ie.gmit.sw.models.Vehicle;
import ie.gmit.sw.rmi.DatabaseClient;
import ie.gmit.sw.rmi.DatabaseService;

@Path("/vehicle")
public class VehicleResource {
	
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();
	private DatabaseService databaseClient;
		
	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/{value}")
	public Response getVehicle(@PathParam("value") int value) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		
		this.vehicles = this.databaseClient.getVehicles();
		
		Vehicle vehicle = null;
		for(Vehicle v : vehicles) {
			if(v.getVehicleId() == value) {
				vehicle = v;
			}
		}
		
		if(vehicle == null) {
			String msg = "The requested order does not exist";
			return Response.status(404).entity(msg).build();  // return 404 for resource not found
		}
		else {
			String msg = getVehicleAsXML(vehicle);
			return Response.status(200).entity(msg).build();
		}		
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML})
	@Path("/createVehicle/{vehicleId}")
	public Response createBooking(@PathParam("vehicleId") int vehicleId, String vehicleXML) throws RemoteException {
		
		Vehicle vehicle = null;
		this.databaseClient = new DatabaseClient();
		for(Vehicle v : vehicles) {
			if(v.getVehicleId() == vehicleId) {
				vehicle = v;
			}
		}
		
		if(vehicle != null) {
			String msg = "The order number " + vehicleId + " already exists";
			return Response.status(409).entity(msg).build();
		}
		else {
			Vehicle newVehicle = getVehicleFromXml(vehicleXML);
			vehicles.add(newVehicle);
			this.databaseClient.createVehicle(newVehicle);
			String msg = "vehicle created!";
			return Response.status(200).entity(msg).build(); // return 201 for resource created
		}
	}

	@PUT
	@Consumes({MediaType.APPLICATION_XML})
	@Path("/updateVehicle/{vehicleId}")
	public Response updateBooking(@PathParam("vehicleId") int vehicleId, String vehicleXML) throws RemoteException {
		Vehicle vehicle = null;
		this.databaseClient = new DatabaseClient();
		for(Vehicle v : vehicles) {
			if(v.getVehicleId() == vehicleId) {
				vehicle = v;
			}
		}
		
		if(vehicle != null) {
			String msg = "The order number " + vehicleId + " already exists";
			return Response.status(409).entity(msg).build();
		}
		else {
			Vehicle updatedVehicle = getVehicleFromXml(vehicleXML);
			this.databaseClient.updateVehicle(updatedVehicle);
			String msg = "Vehicle updated!";
			return Response.status(200).entity(msg).build(); 
		}
	}
	
	@DELETE
	@Produces({MediaType.TEXT_HTML})
	@Path("/deleteVehicle/{vehicleId}")
	public Response deleteCustomer(@PathParam("vehicleId") int vehicleId) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		this.databaseClient.deleteCustomer(vehicleId);
		
		String msg = "vehicle deleted";
		return Response.status(200).entity(msg).build();
	}
	
	private String getVehicleAsXML(Vehicle vo) {
		StringWriter sw = new StringWriter();
		Marshaller m;
		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.models");
			m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(vo, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}		
		return sw.toString();
	}
	
	private Vehicle getVehicleFromXml(String input) {
		StringReader sr1 = new StringReader(input);
		Unmarshaller um1;
		Vehicle voFromXml = null;
		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.models");
			um1 = jc.createUnmarshaller();
			StreamSource source1 = new StreamSource(sr1);
			JAXBElement<Vehicle> voElement1 = um1.unmarshal(source1, Vehicle.class);
			voFromXml = (Vehicle) voElement1.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return voFromXml;
		
	}
	
}

