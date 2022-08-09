package com.juan.estevez.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.juan.estevez.app.commons.GenericServiceApiImpl;
import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.repositories.IDoctorRepository;
import com.juan.estevez.app.services.IDoctorService;

/**
 * Clase de servicio la cual hereda los métodos CRUD de GenericServiceApiImpl
 * por lo cual no sobreescribe ninguno.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Service
public class DoctorServiceImpl extends GenericServiceApiImpl<Doctor, String> implements IDoctorService {

	@Autowired
	private IDoctorRepository doctorRepository;

	@Override
	public CrudRepository<Doctor, String> getRepository() {
		return doctorRepository;
	}

}
