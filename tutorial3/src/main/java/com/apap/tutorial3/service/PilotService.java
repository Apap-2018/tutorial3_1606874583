package com.apap.tutorial3.service;

import com.apap.tutorial3.model.PilotModel;
import java.util.List;

public interface PilotService {
	void addPilot (PilotModel pilot);
	List<PilotModel> getPilotList();
	PilotModel getDetailPilotByLicenseNumber(String lisenceNumber);
	PilotModel updateFlyHour(String lisenceNumber, String value);
	PilotModel deletePilotByLicenseNumber(String lisenceNumber);
}
