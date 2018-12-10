package ie.gmit.sw.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping(value = "/createCustomer", method = RequestMethod.GET)
	public String createCustomerGET(Model model, @ModelAttribute("customer") Customer customer) {	
		
		model.addAttribute("customer", customer);
		
		return "createCustomer";
	}
	
	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public String createCustomerPOST(@Valid @ModelAttribute("customer") Customer customer) {	
		customerService.createCustomer(customer);
		
		return "redirect:customerList";
	}
	
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.GET)
	public String updateCustomerGET(Model model, @ModelAttribute("customer") Customer customer) {	
		
		List<Customer> customers = customerService.getAllCustomers();
		Map<Integer, String> customerList = new HashMap<Integer, String>();
		
		for (Customer c : customers) {
			customerList.put(c.getCustomerId(), c.getFirstName() + " " + c.getLastName());
		}
		
		model.addAttribute("customer", customer);
		model.addAttribute("customerList", customerList);
		
		return "updateCustomer";
	}
	
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public String updateCustomerPOST(@Valid @ModelAttribute("customer") Customer customer) {	
		customerService.updateCustomer(customer);
		return "redirect:customerList";
	}
	
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.GET)
	public String deleteCustomerGET(Model model, @ModelAttribute("customer") Customer customer) {		
		
		List<Customer> customers = customerService.getAllCustomers();
		Map<Integer, String> customerList = new HashMap<Integer, String>();
		
		for (Customer c : customers) {
			customerList.put(c.getCustomerId(), c.getFirstName() + " " + c.getLastName());
		}
		
		model.addAttribute("customer", customer);
		model.addAttribute("customerList", customerList);
		
		return "deleteCustomer";
	}
	
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
	public String deleteCustomerPOST(@Valid @ModelAttribute("customer") Customer customer) {	
		customerService.deleteCustomer(customer);
		return "redirect:customerList";
	}
	
}
