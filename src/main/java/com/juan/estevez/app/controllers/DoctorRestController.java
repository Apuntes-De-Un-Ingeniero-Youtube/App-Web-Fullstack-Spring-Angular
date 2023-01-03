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

import com.juan.estevez.app.dto.DoctorDTO;
import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.services.IDoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorRestController {

	private IDoctorService doctorService;
	private ModelMapper modelMapper;

	@Autowired
	public DoctorRestController(IDoctorService doctorService, ModelMapper modelMapper) {
		this.doctorService = doctorService;
		this.modelMapper = modelMapper;
	}

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
	public ResponseEntity<DoctorDTO> save(@RequestBody DoctorDTO doctorDto) {
		return new ResponseEntity<>(
				modelMapper.map(doctorService.save(modelMapper.map(doctorDto, Doctor.class)), DoctorDTO.class),
				HttpStatus.OK);
	}

	/**
	 * Se encarga de actualizar un médico en el sistema
	 * 
	 * @param medico a actualizar en el sistema
	 * @return médico actualizado en el sistema.
	 */
	@PutMapping
	public ResponseEntity<DoctorDTO> update(@RequestBody DoctorDTO doctorDto) {
		return new ResponseEntity<>(
				modelMapper.map(doctorService.save(modelMapper.map(doctorDto, Doctor.class)), DoctorDTO.class),
				HttpStatus.OK);
	}

	/**
	 * Se encarga de buscar un médico en la base de datos mediante un id pasado como
	 * parámetro en el path o endPoint.
	 * 
	 * @param id por el cuál se va a buscar el médico.
	 * @return Médico encontrado o null en caso de no encontrarlo.
	 */
	@GetMapping("/findById/{idDoctor}")
	public DoctorDTO searchDoctor(@PathVariable String idDoctor) {
		return modelMapper.map(doctorService.get(idDoctor), DoctorDTO.class);
	}

	/**
	 * Se encarga de eliminar un médico de la base de datos por el método get de la
	 * petición http recibida.
	 * 
	 * @param idDoctor por el cuál se buscará el paciente y en caso de encontrarlo
	 *                 se eliminará.
	 * @return médico eliminado en caso de existir.
	 */

	@DeleteMapping("{idDoctor}")
	public ResponseEntity<DoctorDTO> delete(@PathVariable String idDoctor) {
		DoctorDTO doctor = modelMapper.map(doctorService.get(idDoctor), DoctorDTO.class);

		if (doctor != null) {
			doctorService.delete(idDoctor);
			return new ResponseEntity<>(doctor, HttpStatus.OK);
		}
		return null;

	}

}
