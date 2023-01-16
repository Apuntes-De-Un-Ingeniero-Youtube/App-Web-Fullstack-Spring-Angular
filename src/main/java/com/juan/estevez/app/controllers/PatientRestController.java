package com.juan.estevez.app.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.estevez.app.dto.PatientDTO;
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

	private IPatientService patientService;
	private ModelMapper modelMapper;

	@Autowired
	public PatientRestController(IPatientService patientService, ModelMapper modelMapper) {
		this.patientService = patientService;
		this.modelMapper = modelMapper;
	}

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
	public ResponseEntity<PatientDTO> save(@RequestBody PatientDTO patientDto) {
		return new ResponseEntity<>(
				modelMapper.map(
						patientService.save(modelMapper.map(patientDto, Patient.class)), PatientDTO.class), 
				HttpStatus.OK);
	}

	/**
	 * Se encarga de actualizar un paciente en el sistema
	 * 
	 * @param patient a actualizar en el sistema
	 * @return patiente actualizado en el sistema.
	 */
	@PutMapping
	public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO patientDto) {
		return new ResponseEntity<>(
				modelMapper.map(
						patientService.update(modelMapper.map(patientDto, Patient.class)), PatientDTO.class), 
				HttpStatus.OK);
	}

	/**
	 * Se encarga de buscar un paciente en la base de datos mediante un id pasado
	 * como parámetro en el path o endPoint.
	 * 
	 * @param id por el cuál se va a buscar el paciente.
	 * @return Paciente encontrado o null en caso de no encontrarlo.
	 */
	@GetMapping("/findById/{idPatient}")
	public PatientDTO searchPatient(@PathVariable String idPatient) {
		// Aca no devolvemos ninguna entidad de respuesta porque solo estamps consultando datos
		// Para ello solamente debemos mapear la consulta a un objeto DTO y listo.
		return modelMapper.map(patientService.get(idPatient), PatientDTO.class);
	}

	/**
	 * Se encarga de eliminar un paciente de la base de datos por el método get de
	 * la petición http recibida.
	 * 
	 * @param idPatient por el cuál se buscará el paciente y en caso de encontrarlo
	 *                  se eliminará.
	 * @return paciente eliminado en caso de existir.
	 */

	@DeleteMapping("{idPatient}")
	public ResponseEntity<PatientDTO> delete(@PathVariable String idPatient) {
		PatientDTO patient = modelMapper.map(patientService.get(idPatient), PatientDTO.class);

		// Devolvemos el paciente DTO.
		if (patient != null) {
			patientService.delete(idPatient);
			return new ResponseEntity<>(patient, HttpStatus.OK);
		}
		return null;

	}

}
