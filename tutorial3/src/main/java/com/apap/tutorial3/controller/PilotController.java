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
					  @RequestParam(value = "licenseNumber", required = true)String licenseNumber,
					  @RequestParam(value = "name", required = true)String name,
					  @RequestParam(value = "flyHour", required = true)int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getDetailPilotByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", archive);
		return"view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
		
	}
	
	@RequestMapping({"pilot/view/license-number/{licenseNumber}"})
	public String viewWithLNPath(@PathVariable String licenseNumber, Model model) {
		
		PilotModel archive = pilotService.getDetailPilotByLicenseNumber(licenseNumber);
		if(archive == null) {
			return "errorPilotNotFound";
		}
		model.addAttribute("pilot",archive);
		return "view-pilot";
	}
	
	@RequestMapping({"pilot/update/license-number/{licenseNumber}/fly-hour/{value}"})
	public String updateWithLNpath(@PathVariable String licenseNumber,@PathVariable String value, Model model) {
		
		if(pilotService.getDetailPilotByLicenseNumber(licenseNumber).equals(null)) {
			return "errorPilotNotFound";
		}
		
		PilotModel archive = pilotService.updateFlyHour(licenseNumber, value);
		if(archive == null) {
			return "errorValueNotInteger";
		} 
		
		model.addAttribute("pilot",archive);
		return "view-pilot-change";
	}
	
	@RequestMapping({"pilot/delete/license-number/{licenseNumber}"})
	public String updateWithLNpath(@PathVariable String licenseNumber, Model model) {
		
		if(!pilotService.getDetailPilotByLicenseNumber(licenseNumber).equals(null)) {
			
			PilotModel archive = pilotService.deletePilotByLicenseNumber(licenseNumber);
			model.addAttribute("pilot",archive);
			return "view-pilot-change";
			
		}else {
			return "errorPilotNotFound";
		}
		
		 
		
		
	}
}
