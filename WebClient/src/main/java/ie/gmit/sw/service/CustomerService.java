package ie.gmit.sw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import ie.gmit.sw.models.Customer;

@Service
public class CustomerService {
	
	private String url = "http://localhost:8080/WebService/webapi/customer/";

	public List<Customer> getAllCustomers() {
        Client client = Client.create();
        WebResource webresource = client.resource(this.url);
                             
        return webresource.get(new GenericType<List<Customer>>(){});
    }
	
	public void createCustomer(Customer customer) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(this.url);

		ClientResponse response = webResource.type("application/xml")
		   .post(ClientResponse.class, customer);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
	}
	
	public void updateCustomer(Customer customer) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(this.url + customer.getCustomerId());

		ClientResponse response = webResource.type("application/xml")
		   .put(ClientResponse.class, customer);
		
		System.out.println("Response code: " + response.getStatus());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
	}
	
	public void deleteCustomer(Customer customer) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(this.url + customer.getCustomerId());

		ClientResponse response = webResource.delete(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
	}
	
}
