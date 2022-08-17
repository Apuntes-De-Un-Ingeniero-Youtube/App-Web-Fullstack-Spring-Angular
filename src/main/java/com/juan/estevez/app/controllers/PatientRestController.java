package com.juan.estevez.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	/**
	 * Se encarga de insertar un paciente en el sistema
	 * 
	 * @param patient a insertar en el sistema
	 * @return patiente insertado en el sistema.
	 */
	@PostMapping
	public Patient save(@RequestBody Patient patient) {
		return patientService.save(patient);
	}

	/**
	 * Se encarga de actualizar un paciente en el sistema
	 * 
	 * @param patient a actualizar en el sistema
	 * @return patiente actualizado en el sistema.
	 */
	@PutMapping
	public Patient update(@RequestBody Patient patient) {
		return patientService.update(patient);
	}

	/**
	 * Se encarga de buscar un paciente en la base de datos mediante un id pasado
	 * como parámetro en el path o endPoint.
	 * 
	 * @param id por el cuál se va a buscar el paciente.
	 * @return Paciente encontrado o null en caso de no encontrarlo.
	 */
	@GetMapping("/findById/{idPatient}")
	public Patient searchPatient(@PathVariable String idPatient) {
		return patientService.get(idPatient);
	}

	/**
	 * Se encarga de eliminar un paciente de la base de datos por el método get de
	 * la petición http recibida.
	 * 
	 * @param idPatient por el cuál se buscará el paciente y en caso de encontrarlo
	 *                  se eliminará.
	 * @return paciente eliminado en caso de existir.
	 
	@DeleteMapping("{idPatient}")
	public Patient delete(@PathVariable String idPatient) {
		Patient patient = patientService.get(idPatient);
		patientService.delete(idPatient);
		return patient;
	}*/

}
