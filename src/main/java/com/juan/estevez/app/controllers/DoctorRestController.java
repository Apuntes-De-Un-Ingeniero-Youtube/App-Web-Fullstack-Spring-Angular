package com.juan.estevez.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.services.IDoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorRestController {

	@Autowired
	private IDoctorService doctorService;

	/**
	 * Se encarga de devolver una lista de los médicos registrados en el sistema.
	 * 
	 * @return listado de médicos encontrados.
	 */
	@GetMapping("/findAll")
	public List<Doctor> listDoctors() {
		return doctorService.list();
	}

	/**
	 * Se encarga de insertar un médico en el sistema
	 * 
	 * @param médico a insertar en el sistema
	 * @return médico insertado en el sistema.
	 */
	@PostMapping
	public Doctor save(@RequestBody Doctor doctor) {
		return doctorService.save(doctor);
	}

	/**
	 * Se encarga de actualizar un médico en el sistema
	 * 
	 * @param medico a actualizar en el sistema
	 * @return médico actualizado en el sistema.
	 */
	@PutMapping
	public Doctor update(@RequestBody Doctor doctor) {
		return doctorService.update(doctor);
	}

	/**
	 * Se encarga de buscar un médico en la base de datos mediante un id pasado
	 * como parámetro en el path o endPoint.
	 * 
	 * @param id por el cuál se va a buscar el médico.
	 * @return Médico encontrado o null en caso de no encontrarlo.
	 */
	@GetMapping("/findById/{idDoctor}")
	public Doctor searchDoctor(@PathVariable String idDoctor) {
		return doctorService.get(idDoctor);
	}

	/**
	 * Se encarga de eliminar un médico de la base de datos por el método get de
	 * la petición http recibida.
	 * 
	 * @param idDoctor por el cuál se buscará el paciente y en caso de encontrarlo
	 *                  se eliminará.
	 * @return médico eliminado en caso de existir.
	 */
	 
	@DeleteMapping("{idDoctor}")
	public Doctor delete(@PathVariable String idDoctor) {
		Doctor doctor = doctorService.get(idDoctor);
		if (doctor != null) {
			doctorService.delete(idDoctor);
			return doctor;
		}
			return null;
		
	}

}
