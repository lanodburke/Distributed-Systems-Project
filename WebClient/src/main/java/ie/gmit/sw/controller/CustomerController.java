package ie.gmit.sw.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.gmit.sw.models.Customer;
import ie.gmit.sw.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/customerList", method = RequestMethod.GET)
	public String viewAllBookings(Model model) throws IOException, JAXBException {
		this.customerService = new CustomerService();
		
		List<Customer> customers = customerService.getAllCustomers();
		
		
		model.addAttribute("customers", customers);
		
		return "customerList";
	}
	
}
