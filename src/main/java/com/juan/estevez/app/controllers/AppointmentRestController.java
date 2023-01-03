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

import com.juan.estevez.app.dto.AppointmentDTO;
import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.services.IAppointmentService;

/**
 * Controlador de tipo REST para la entidad Appointment
 * 
 * @author Juan Carlos Etsvez Vargas.
 *
 */
@RestController
@RequestMapping("/appointment")
public class AppointmentRestController {

	private IAppointmentService appointmentService;
	private ModelMapper modelMapper;

	@Autowired
	public AppointmentRestController(IAppointmentService appointmentService, ModelMapper modelMapper) {
		this.appointmentService = appointmentService;
		this.modelMapper = modelMapper;
	}

	/**
	 * Obtiene un listado con todas las citas existentes en el sistema.
	 * 
	 * @return Lista con las citas obtenidas.
	 */
	@GetMapping("/findAll")
	public List<Appointment> listAppointments() {
		return appointmentService.list();
	}

	/**
	 * Inserta una cita en el sistema.
	 * 
	 * @param appointment a insertar en el sistema.
	 * @return null el caso de que no se cumplan las reglas de negocio o Appointment
	 *         con el registro insertado.
	 */
	@PostMapping
	public ResponseEntity<AppointmentDTO> saveAppointment(@RequestBody AppointmentDTO appointmentDto) {
		return new ResponseEntity<>(modelMapper
				.map(appointmentService.save(modelMapper.map(appointmentDto, Appointment.class)), AppointmentDTO.class),
				HttpStatus.OK);
	}

	/**
	 * Actualiza una cita en el sistema y en caso de no encontrarla la insertará.
	 * 
	 * @param appointment a actualizar en el sistema.
	 * @return null el caso de que no se cumplan las reglas de negocio o Appointment
	 *         con el registro actualizado.
	 */
	@PutMapping
	public ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody AppointmentDTO appointmentDto) {
		return new ResponseEntity<>(
				modelMapper.map(appointmentService.update(modelMapper.map(appointmentDto, Appointment.class)), AppointmentDTO.class),
				HttpStatus.OK);
	}

	/**
	 * Obtiene una cita en específica
	 * 
	 * @param idAppointment por el cual se buscará la cita en el sistema.
	 * @return null el caso de que no se cumplan las reglas de negocio o Appointment
	 *         con el registro encontrado.
	 */
	@GetMapping("/findById/{idAppointment}")
	public AppointmentDTO findAppointmentById(@PathVariable Integer idAppointment) {
		return modelMapper.map(appointmentService.get(idAppointment), AppointmentDTO.class);
	}

	/**
	 * Elimina un registro del sistema (Appointment).
	 * 
	 * @param idAppointment por el cual se buscará y se eliminará la cita
	 */
	@DeleteMapping("/{idAppointment}")
	public ResponseEntity<AppointmentDTO> deleteAppointment(@PathVariable Integer idAppointment) {
		AppointmentDTO appointment = modelMapper.map(appointmentService.get(idAppointment), AppointmentDTO.class);

		if (appointment != null) {
			appointmentService.delete(idAppointment);
			return new ResponseEntity<>(appointment, HttpStatus.OK);
		}
		return null;
	}
}
