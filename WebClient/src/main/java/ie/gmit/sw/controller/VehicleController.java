package ie.gmit.sw.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.gmit.sw.models.Vehicle;
import ie.gmit.sw.service.VehicleService;

@Controller
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(value = "/vehicleList", method = RequestMethod.GET)
	public String viewAllBookings(Model model) throws IOException, JAXBException {
		this.vehicleService = new VehicleService();
		
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		
		
		model.addAttribute("vehicles", vehicles);
		
		return "vehicleList";
	}
	
}
