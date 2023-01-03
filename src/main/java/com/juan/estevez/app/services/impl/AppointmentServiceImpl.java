package com.juan.estevez.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.juan.estevez.app.commons.GenericServiceApiImpl;
import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.repositories.IAppointmentRepository;
import com.juan.estevez.app.services.IAppointmentService;
import com.juan.estevez.app.services.IDoctorService;

/**
 * Clase hija de GnericServiceImpl implementa la interface IAppointmentService
 * 
 * @author Juan Carlos Estevez Vargas.
 */
@Service
public class AppointmentServiceImpl extends GenericServiceApiImpl<Appointment, Integer> implements IAppointmentService {

	@Autowired
	private IAppointmentRepository appointmentRepository;

	@Autowired
	private IDoctorService doctorService;

	@Override
	public CrudRepository<Appointment, Integer> getRepository() {
		return appointmentRepository;
	}

	/**
	 * Comprueba si la cita a insertar está dentro del rango de atención del médico.
	 * 
	 * @param entity a evaluar
	 * @return null en caso de que la cita a insertar este fuera del rango de
	 *         atención del médico, en caso contrario, inserta la cita en la base de
	 *         datos.
	 */
	public Appointment verify(Appointment entity, String idDoctor) {
		Doctor doctor = doctorService.get(idDoctor);
		if (entity.getHour() >= doctor.getAttentionStartTime() && entity.getHour() <= doctor.getAttentionEndTime()) {
			return super.save(entity);
		}
		return null;
	}

	/**
	 * Evalúa si ya existe una cita con el mismo doctor y paciente dentro de la base
	 * de datos.
	 * 
	 * @param appointment a evaluar.
	 * @return null en caso de que ya exista la cita o llamado al método verify() en
	 *         caso de que no exista la cita.
	 */
	@Override
	public Appointment save(Appointment appointment) {
		String idDoctor = appointment.getDoctor();
		String idPatient = appointment.getPatient();
		String date = appointment.getDate();

		List<Appointment> appointments = (List<Appointment>) appointmentRepository.findAll();

		Optional<Appointment> appointmentOpcional = appointments.stream().filter(app -> (app.getDate().equals(date)
				&& app.getDoctor().equals(idDoctor) && app.getPatient().equals(idPatient))).findAny();
		
		if (!appointmentOpcional.isEmpty()) return null;
		
		return verify(appointment, idDoctor);
	}

}
