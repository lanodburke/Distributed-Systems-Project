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

import ie.gmit.sw.models.Customer;
import ie.gmit.sw.rmi.DatabaseClient;
import ie.gmit.sw.rmi.DatabaseService;

@Path("/customer")
public class CustomerResource {
	
	private List<Customer> customers = new ArrayList<Customer>();
	private DatabaseService databaseClient;
		
	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/{value}")
	public Response getCustomer(@PathParam("value") int value) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		
		this.customers = this.databaseClient.getCustomers();
		
		Customer customer = null;
		for(Customer c : customers) {
			if(c.getCustomerId() == value) {
				customer = c;
			}
		}
		
		if(customer == null) {
			String msg = "The requested order does not exist";
			return Response.status(404).entity(msg).build();  // return 404 for resource not found
		}
		else {
			String msg = getCustomerAsXML(customer);
			return Response.status(200).entity(msg).build();
		}		
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML})
	@Path("/createCustomer/{customerId}")
	public Response createBooking(@PathParam("customerId") int customerId, String customerXML) throws RemoteException {
		
		Customer customer = null;
		this.databaseClient = new DatabaseClient();
		for(Customer c : customers) {
			if(c.getCustomerId() == customerId) {
				customer = c;
			}
		}
		
		if(customer != null) {
			String msg = "The order number " + customerId + " already exists";
			return Response.status(409).entity(msg).build();
		}
		else {
			Customer newCustomer = getCustomerFromXml(customerXML);
			customers.add(newCustomer);
			this.databaseClient.createCustomer(newCustomer);
			String msg = "Customer created!";
			return Response.status(200).entity(msg).build(); // return 201 for resource created
		}
	}

	@PUT
	@Consumes({MediaType.APPLICATION_XML})
	@Path("/updateCustomer/{customerId}")
	public Response updateBooking(@PathParam("customerId") int customerId, String customerXML) throws RemoteException {
		Customer customer = null;
		this.databaseClient = new DatabaseClient();
		for(Customer c : customers) {
			if(c.getCustomerId() == customerId) {
				customer = c;
			}
		}
		
		if(customer != null) {
			String msg = "The order number " + customerId + " already exists";
			return Response.status(409).entity(msg).build();
		}
		else {
			Customer updatedCustomer = getCustomerFromXml(customerXML);
			this.databaseClient.updateCustomer(updatedCustomer);
			String msg = "Booking updated!";
			return Response.status(200).entity(msg).build(); 
		}
	}
	
	@DELETE
	@Produces({MediaType.TEXT_HTML})
	@Path("/deleteCustomer/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") int customerId) throws RemoteException {
		this.databaseClient = new DatabaseClient();
		this.databaseClient.deleteCustomer(customerId);
		
		String msg = "Customer deleted";
		return Response.status(200).entity(msg).build();
	}
	
	private String getCustomerAsXML(Customer co) {
		StringWriter sw = new StringWriter();
		Marshaller m;
		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.models");
			m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(co, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}		
		return sw.toString();
	}
	
	private Customer getCustomerFromXml(String input) {
		StringReader sr1 = new StringReader(input);
		Unmarshaller um1;
		Customer coFromXml = null;
		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.models");
			um1 = jc.createUnmarshaller();
			StreamSource source1 = new StreamSource(sr1);
			JAXBElement<Customer> coElement1 = um1.unmarshal(source1, Customer.class);
			coFromXml = (Customer) coElement1.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return coFromXml;
		
	}
	
}

