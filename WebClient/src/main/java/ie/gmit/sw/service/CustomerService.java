package ie.gmit.sw.service;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import ie.gmit.sw.models.Customer;

@Service
public class CustomerService {
	
	private String url = "http://localhost:8080/WebService/webapi/customer/";

	public List<Customer> getAllCustomers() {
        Client client = Client.create();
        WebResource webresource = client.resource(this.url + "customers/");
                             
        return webresource.get(new GenericType<List<Customer>>(){});
    }
	
	public void createCustomer(Customer customer) {
        Client client = Client.create();        
        WebResource webResource = client.resource(this.url + "createCustomer/" + customer.getCustomerId());
        webResource.type(MediaType.APPLICATION_XML)
          .accept(MediaType.APPLICATION_XML)
          .post(Customer.class, customer);
        return;
    }
}
