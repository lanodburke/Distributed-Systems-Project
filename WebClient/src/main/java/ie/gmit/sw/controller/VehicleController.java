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

import ie.gmit.sw.models.Vehicle;
import ie.gmit.sw.service.VehicleService;

@Controller
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(value = "/vehicleList", method = RequestMethod.GET)
	public String viewAllVehicles(Model model) throws IOException, JAXBException {
		this.vehicleService = new VehicleService();
		
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		
		
		model.addAttribute("vehicles", vehicles);
		
		return "vehicleList";
	}
	
	@RequestMapping(value = "/createVehicle", method = RequestMethod.GET)
	public String createVehicleGET(Model model, @ModelAttribute("vehicle") Vehicle vehicle) {	
		
		model.addAttribute("vehicle", vehicle);
		
		return "createVehicle";
	}
	
	@RequestMapping(value = "/createVehicle", method = RequestMethod.POST)
	public String createVehiclePOST(@Valid @ModelAttribute("vehicle") Vehicle vehicle) {	
		vehicleService.createVehicle(vehicle);
		
		return "redirect:vehicleList";
	}
	
	@RequestMapping(value = "/updateVehicle", method = RequestMethod.GET)
	public String updateVehicleGET(Model model, @ModelAttribute("vehicle") Vehicle vehicle) {	
		
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		Map<Integer, String> vehicleList = new HashMap<Integer, String>();
		
		for (Vehicle v : vehicles) {
			vehicleList.put(v.getVehicleId(), v.getRegistrationNumber());
		}
		
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("vehicleList", vehicleList);
		
		return "updateVehicle";
	}
	
	@RequestMapping(value = "/updateVehicle", method = RequestMethod.POST)
	public String updateVehiclePOST(@Valid @ModelAttribute("vehicle") Vehicle vehicle) {	
		vehicleService.updateVehicle(vehicle);
		return "redirect:vehicleList";
	}
	
	@RequestMapping(value = "/deleteVehicle", method = RequestMethod.GET)
	public String deleteVehicleGET(Model model, @ModelAttribute("vehicle") Vehicle vehicle) {		
		
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		Map<Integer, String> vehicleList = new HashMap<Integer, String>();
		
		for (Vehicle v : vehicles) {
			vehicleList.put(v.getVehicleId(), v.getRegistrationNumber());
		}
		
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("vehicleList", vehicleList);
		
		return "deleteVehicle";
	}
	
	@RequestMapping(value = "/deleteVehicle", method = RequestMethod.POST)
	public String deleteVehiclePOST(@Valid @ModelAttribute("vehicle") Vehicle vehicle) {	
		vehicleService.deleteVehicle(vehicle);
		return "redirect:vehicleList";
	}
	
}
