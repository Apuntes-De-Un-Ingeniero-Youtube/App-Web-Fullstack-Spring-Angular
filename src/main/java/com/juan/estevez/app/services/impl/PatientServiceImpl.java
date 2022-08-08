package com.juan.estevez.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.juan.estevez.app.commons.GenericServiceApiImpl;
import com.juan.estevez.app.entities.Patient;
import com.juan.estevez.app.repositories.IPatientRepository;
import com.juan.estevez.app.services.IPatientService;

public class PatientServiceImpl extends GenericServiceApiImpl<Patient, String> implements IPatientService{

	@Autowired
	private IPatientRepository patientRepository;
	
	@Override
	public CrudRepository<Patient, String> getRepository() {
		return patientRepository;
	}

}
