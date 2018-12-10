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

import ie.gmit.sw.models.Customer;
import ie.gmit.sw.rmi.DatabaseClient;
import ie.gmit.sw.rmi.DatabaseService;

@Path("/customer")
public class CustomerResource {
	
	private List<Customer> customers = new ArrayList<Customer>();
	private DatabaseService databaseClient;
	
	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/")
	public Response getAllCustomers() throws RemoteException {
		this.databaseClient = new DatabaseClient();
		
		this.customers = this.databaseClient.getCustomers();
		
		final GenericEntity<List<Customer>> entity = new GenericEntity<List<Customer>>(customers) { };
		
		return Response.status(200).entity(entity).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML})
	@Path("/")
	public Response createCustomer(Customer newCustomer) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		customers.add(newCustomer);
		this.databaseClient.createCustomer(newCustomer);
		String msg = "Customer created!";
		return Response.status(200).entity(msg).build(); // return 201 for resource created
	}

	@PUT
	@Consumes({MediaType.APPLICATION_XML})
	@Path("/")
	public Response updateCustomer(Customer updatedCustomer) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		this.databaseClient.updateCustomer(updatedCustomer);
		String msg = "Booking updated!";
		return Response.status(200).entity(msg).build(); 
	}
	
	@DELETE
	@Produces({MediaType.TEXT_HTML})
	@Path("/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") int customerId) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		this.databaseClient.deleteCustomer(customerId);
		
		String msg = "Customer deleted";
		return Response.status(200).entity(msg).build();
	}	
}

