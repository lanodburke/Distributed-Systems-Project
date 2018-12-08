package ie.gmit.sw.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import ie.gmit.sw.http.HttpClient;
import ie.gmit.sw.models.Booking;

@Controller
public class BookingController {
	
	@Autowired
	private HttpClient client;
	
	@RequestMapping("/buzz")
	public String SUCK() {
		return "index";
	}
	
	@RequestMapping(value = "/booking", method = RequestMethod.GET)
	public String checkBookingIDGET(Model model) {
	
		Booking booking = new Booking();
		
		model.addAttribute("booking",booking);
		
		return "booking";
	}
	
	@RequestMapping(value = "/bookingList", method = RequestMethod.GET)
	public String viewAllBookings(Model model) throws IOException, ParserConfigurationException, SAXException {
		
		this.client = new HttpClient();
		
		client.getBookings();
		
//		model.addAttribute("bookingList", bookings);
		
		return "bookingList";
	}
	
	@RequestMapping(value = "/booking", method = RequestMethod.POST)
	public String checkBookingIDPOST(@Valid @ ModelAttribute("booking") Booking booking, Model model) {
		
		System.out.println(booking.getBookingId());
		
		return "index";
	}
}
