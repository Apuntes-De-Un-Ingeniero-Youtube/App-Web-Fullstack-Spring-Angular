package com.juan.estevez.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.estevez.app.entities.Patient;
import com.juan.estevez.app.services.IPatientService;

/**
 * Controlador de tipo REST para manejar la entidad Patient.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@RestController
@RequestMapping("/patient")
public class PatientRestController {

	@Autowired
	private IPatientService patientService;

	/**
	 * Se encarga de devolver una lista de los pacientes registrados en el sistema.
	 * 
	 * @return listado de pacientes encontrados.
	 */
	@GetMapping("/findAll")
	public List<Patient> listPatients() {
		return patientService.list();
	}
}
