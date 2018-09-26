package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true)String id,
			@RequestParam(value = "licenseNumber", required = true) String licenseNumber,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "flyHour", required = true) int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping("/pilot/view")
		public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
			PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
			
			model.addAttribute("pilot", archive);
			return "view-pilot";
		}
	
	@RequestMapping("/pilot/viewall") 
		public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		
		model.addAttribute("pilotList", archive);
		return "viewall-pilot";
	}
	
	@RequestMapping("pilot/view/license-number/{lcsNumber}")
	public String pilotPage(@PathVariable String lcsNumber, Model model) {
		PilotModel viewPilot = pilotService.getPilotDetailByLicenseNumber(lcsNumber);
		
		if (viewPilot == null) {
			return "view-error";
		} else {
			model.addAttribute("pilot", viewPilot);
			return "view-profile";
		}
	}
	
	@RequestMapping("pilot/update/license-number/{lcsNumber}/fly-hour/{flyNumber}")
	public String updateHour(@PathVariable String lcsNumber, @PathVariable Optional<Integer> flyNumber, Model model) {
		PilotModel viewPilot = pilotService.getPilotDetailByLicenseNumber(lcsNumber);
		
		if (viewPilot == null) {
			return "view-error";
		} else {
			viewPilot.setFlyHour(flyNumber.get());
			model.addAttribute("pilot", viewPilot);
			return "update-profile";
		}
	}
	
	@RequestMapping("pilot/delete/id/{idPilot}")
	public String deleteId(@PathVariable String idPilot, Model model) {
		PilotModel viewPilot = pilotService.getPilotDetailById(idPilot);
		
		if (viewPilot == null) {
			return "delete-error";
		} else {
			for (int j = 0; j < pilotService.getPilotList().size(); j++) {
				if (pilotService.getPilotList().get(j).getId().equalsIgnoreCase(idPilot)) {
					pilotService.getPilotList().remove(j);
				}
			}
			return "delete-pilot";
		}
	}
}