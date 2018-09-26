package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.PilotModel;

@Service
public class PilotInMemoryService implements PilotService{
private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		archivePilot.add(pilot);
	}

	@Override
	public List<PilotModel> getPilotList() {
		return archivePilot;
	}

	@Override
	public PilotModel getDetailPilotByLicenseNumber(String lisenceNumber) {
		for(PilotModel pilot : archivePilot) {
	        if(pilot.getLicenseNumber().equals(lisenceNumber)) {
	            return pilot;
	        }
	    }
	    return null;
	}

	@Override
	public PilotModel updateFlyHour(String lisenceNumber, String value) {
		if(value.matches("\\d+")) {
			for(PilotModel pilot : archivePilot) {
		        if(pilot.getLicenseNumber().equals(lisenceNumber)) {
		            pilot.setFlyHour(Integer.parseInt(value));
		            return pilot;
		        }
			}
		}
	    return null;		
	}

	@Override
	public PilotModel deletePilotByLicenseNumber(String lisenceNumber) {
		for(PilotModel pilot : archivePilot) {
			if(pilot.getLicenseNumber().equals(lisenceNumber)) {
				archivePilot.remove(pilot);
				return(pilot);
			}
		}return null;
	}


	

	
}
