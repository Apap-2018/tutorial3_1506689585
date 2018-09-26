package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.PilotModel;

@Service
public class PilotInMemoryService implements PilotService {
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
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		PilotModel result = null;
		
		for (int i=0; i < archivePilot.size(); i++) {
			if (archivePilot.get(i).getLicenseNumber().equals(licenseNumber)) {
				result = archivePilot.get(i);
			}
		}
		return result;
	}
	
	@Override
	public PilotModel getPilotDetailById(String id) {
		PilotModel result = null;
		
		for (int i=0; i < archivePilot.size(); i++) {
			if (archivePilot.get(i).getId().equals(id)) {
				result = archivePilot.get(i);
			}
		}
		return result;
	}
}
