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
	public String createBookingGET(Model model, @ModelAttribute("booking") Booking booking) {	
		
		List<Customer> customers = customerService.getAllCustomers();
		Map<Integer, String> customerList = new HashMap<Integer, String>();
		
		for (Customer customer : customers) {
			customerList.put(customer.getCustomerId(), customer.getFirstName() + " " + customer.getLastName());
		}
		
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		Map<Integer, String> vehicleList = new HashMap<Integer, String>();
		
		for (Vehicle vehicle : vehicles) {
			vehicleList.put(vehicle.getVehicleId(), vehicle.getRegistrationNumber());
		}
		
		model.addAttribute("booking", booking);
		model.addAttribute("customerList", customerList);
		model.addAttribute("vehicleList", vehicleList);
		
		return "createBooking";
	}
	
	@RequestMapping(value = "/createBooking", method = RequestMethod.POST)
	public String createBookingPOST(@Valid @ModelAttribute("booking") Booking booking) {	
		bookingService.createBooking(booking);
		
		return "redirect:bookingList";
	}
	
	@RequestMapping(value = "/updateBooking", method = RequestMethod.GET)
	public String updateBookingGET(Model model, @ModelAttribute("booking") Booking booking) {	
		
		List<Booking> bookings = bookingService.getAllBookings();
		Map<Integer, String> bookingList = new HashMap<Integer, String>();
		
		for (Booking b : bookings) {
			bookingList.put(b.getBookingId(),b.getCustomer().getFirstName() + " " + b.getCustomer().getLastName() +  " : " + b.getBookingStartDate() + " - " + b.getBookingEndDate());
		}
		
		List<Customer> customers = customerService.getAllCustomers();
		Map<Integer, String> customerList = new HashMap<Integer, String>();
		
		for (Customer customer : customers) {
			customerList.put(customer.getCustomerId(), customer.getFirstName() + " " + customer.getLastName());
		}
		
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		Map<Integer, String> vehicleList = new HashMap<Integer, String>();
		
		for (Vehicle vehicle : vehicles) {
			vehicleList.put(vehicle.getVehicleId(), vehicle.getRegistrationNumber());
		}
		
		model.addAttribute("booking", booking);
		model.addAttribute("bookingList", bookingList);
		model.addAttribute("customerList", customerList);
		model.addAttribute("vehicleList", vehicleList);
		
		return "updateBooking";
	}
	
	@RequestMapping(value = "/updateBooking", method = RequestMethod.POST)
	public String updateBookingPOST(@Valid @ModelAttribute("booking") Booking booking) {	
		bookingService.updateBooking(booking);
		return "redirect:bookingList";
	}
	
	@RequestMapping(value = "/deleteBooking", method = RequestMethod.GET)
	public String deleteBookingGET(Model model, @ModelAttribute("booking") Booking booking) {	
		
		List<Booking> bookings = bookingService.getAllBookings();
		Map<Integer, String> bookingList = new HashMap<Integer, String>();
		
		for (Booking b : bookings) {
			bookingList.put(b.getBookingId(),b.getCustomer().getFirstName() + " " + b.getCustomer().getLastName() +  " : " + b.getBookingStartDate() + " - " + b.getBookingEndDate());
		}
				
		model.addAttribute("booking", booking);
		model.addAttribute("bookingList", bookingList);
		
		return "deleteBooking";
	}
	
	@RequestMapping(value = "/deleteBooking", method = RequestMethod.POST)
	public String deleteBookingPOST(@Valid @ModelAttribute("booking") Booking booking) {	
		bookingService.deleteBooking(booking);
		return "redirect:bookingList";
	}
	
}
