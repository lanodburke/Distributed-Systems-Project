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

import ie.gmit.sw.models.Booking;
import ie.gmit.sw.models.Customer;
import ie.gmit.sw.models.Vehicle;
import ie.gmit.sw.service.BookingService;
import ie.gmit.sw.service.CustomerService;
import ie.gmit.sw.service.VehicleService;

@Controller
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/bookingList", method = RequestMethod.GET)
	public String viewAllBookings(Model model) throws IOException, JAXBException {
		this.bookingService = new BookingService();
		
		List<Booking> bookings = bookingService.getAllBookings();
		
		model.addAttribute("bookings", bookings);
		
		return "bookingList";
	}
	
	@RequestMapping(value = "/createBooking", method = RequestMethod.GET)
	public String createBooking(Model model, @ModelAttribute("booking") Booking booking) throws IOException, JAXBException {	
		List<Customer> customers = customerService.getAllCustomers();
		Map<Integer, String> customerList = new HashMap<Integer, String>();
		
		for (Customer customer : customers) {
			customerList.put(customer.getCustomerId(), customer.getFirstName() + " " + customer.getLastName());
		}
		
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		Map<Integer, String> vehicleList = new HashMap<Integer, String>();
		
		for (Vehicle vehicle : vehicles) {
			vehicleList.put(vehicle.getVehicleId(), vehicle.getRegistrationNumber() + " " + vehicle.getMileage());
		}
		
		model.addAttribute("booking", booking);
		model.addAttribute("customerList", customerList);
		model.addAttribute("vehicleList", vehicleList);
		
		return "createBooking";
	}
	
	@RequestMapping(value = "/createBooking", method = RequestMethod.POST)
	public String createBooking(@Valid @ModelAttribute("booking") Booking booking) throws IOException, JAXBException {	
		bookingService.createBooking(booking);
		
		return "redirect:bookingList";
	}
}
