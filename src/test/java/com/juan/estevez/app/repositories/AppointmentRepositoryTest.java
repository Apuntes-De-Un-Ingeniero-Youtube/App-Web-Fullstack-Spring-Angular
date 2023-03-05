package com.juan.estevez.app.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.entities.Patient;

@SpringBootTest
public class AppointmentRepositoryTest {

	@Autowired
	private IAppointmentRepository appointmentRepository;

	private Appointment appointment;
	private Doctor doctor;
	private Patient patient;

	@BeforeEach
	void setup() {
		patient = new Patient();
		patient.setIdPatient("2087");
		patient.setName("Patient Repository Test");
		patient.setIdType("CC");
		patient.setDateOfBirth("1987-02-12");
		patient.setEps("Eps Test");
		patient.setHistoryClinic("OK");

		doctor = new Doctor();
		doctor.setIdDoctor("1010");
		doctor.setDoctorsName("Doctor List Test");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("1111");
		doctor.setYearsExperience(4);
		doctor.setSpecialty("Cardiología");
		doctor.setAttentionStartTime(8.0);
		doctor.setAttentionEndTime(16.0);

		appointment = new Appointment();
		appointment.setIdAppointment(13);
		appointment.setDate("2024-11-08");
		appointment.setDoctor(doctor.getIdDoctor());
		appointment.setPatient(patient.getIdPatient());
		appointment.setHour(14);
	}

	@Test
	void testSaveAppointmentRepository() {
		// when
		Appointment appointmentSaved = appointmentRepository.save(appointment);

		// then
		assertThat(appointmentSaved).isNotNull();
		assertThat(appointmentSaved.getDoctor()).isEqualTo(appointment.getDoctor());
		assertThat(appointmentSaved.getPatient()).isEqualTo(appointment.getPatient());
	}

	@Test
	void testListAppointmentsRepository() {
		// given
		Appointment appointment2 = new Appointment();
		appointment.setIdAppointment(300);
		appointment.setDate("2024-11-08");
		appointment.setDoctor(doctor.getIdDoctor());
		appointment.setPatient(patient.getIdPatient());
		appointment.setHour(15);

		List<Appointment> list = new ArrayList<>();
		list.add(appointment);
		list.add(appointment2);
		appointmentRepository.saveAll(list);

		// when
		List<Appointment> appointmentList = (List<Appointment>) appointmentRepository.findAll();

		// then
		assertThat(appointmentList).isNotNull();
		assertThat(appointmentList.size()).isEqualTo(2);
	}
	
	@Test
	void testGetAppointmentRepository() {
		// given
		appointmentRepository.save(appointment);
		
		// when
		Optional<Appointment> appointmentDB = appointmentRepository.findById(appointment.getIdAppointment());
		
		// then
		assertThat(appointmentDB).isPresent();
	}
	
	@Test
	void testUpdateAppointmentRepository() {
		// given
		appointmentRepository.save(appointment);
		
		appointment.setDate("2025-11-23");
		appointment.setHour(8);
		
		// when
		Appointment appointmentUpdated = appointmentRepository.save(appointment);
		
		// then
		assertThat(appointmentUpdated).isNotNull();
		assertThat(appointmentUpdated.getDate()).isEqualTo(appointment.getDate());
	}
	
	@Test
	void testDeleteAppointmentRepository() {
		// given
		appointmentRepository.save(appointment);
		
		// when
		appointmentRepository.deleteById(appointment.getIdAppointment());
		Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointment.getIdAppointment());
		
		// then
		assertThat(appointmentOptional).isEmpty();
	}

}
