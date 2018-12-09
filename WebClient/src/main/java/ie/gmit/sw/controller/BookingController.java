package ie.gmit.sw.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.gmit.sw.http.HttpClient;
import ie.gmit.sw.models.Booking;

@Controller
public class BookingController {
	
	@Autowired
	private HttpClient client;
	
	@RequestMapping(value = "/booking", method = RequestMethod.GET)
	public String checkBookingIDGET(Model model) {
	
		Booking booking = new Booking();
		
		model.addAttribute("booking",booking);
		
		return "booking";
	}
	
	@RequestMapping(value = "/bookingList", method = RequestMethod.GET)
	public String viewAllBookings(Model model) throws IOException, JAXBException {
		this.client = new HttpClient();
		
		List<Booking> bookings = client.getAllBookings();
		
		for (Booking booking : bookings) {
			System.out.println(booking.getBookingId());
		}
		
		model.addAttribute("bookings", bookings);
		
		return "bookingList";
	}
	
	@RequestMapping(value = "/booking", method = RequestMethod.POST)
	public String checkBookingIDPOST(@Valid @ ModelAttribute("booking") Booking booking, Model model) throws IOException, JAXBException {
		
		int bookingId = booking.getBookingId();
		
		System.out.println(bookingId);
		
		return "bookingList";
	}
	
	@RequestMapping(value = "/createBooking", method = RequestMethod.GET)
	public String gotoCreateBooking(Model model) throws IOException, JAXBException {	
		Booking booking = new Booking();
		
		model.addAttribute("booking", booking);
		
		return "bookingList";
	}
	
	@RequestMapping(value = "/createBooking", method = RequestMethod.POST)
	public String createBooking(@Valid @ ModelAttribute("booking") Booking booking, Model model) throws IOException, JAXBException {
		
		int bookingId = booking.getBookingId();
		
		this.client = new HttpClient();
		
		client.createBooking(booking);
		
		return "index";
	}
	
}
