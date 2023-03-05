package com.juan.estevez.app.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.juan.estevez.app.entities.Doctor;

@SpringBootTest
public class DoctorRepositoryTest {

	@Autowired
	private IDoctorRepository doctorRepository;

	private Doctor doctor;

	@BeforeEach
	void setup() {
		doctor = new Doctor();
		doctor.setIdDoctor("20880");
		doctor.setDoctorsName("Doctor Repository Test");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("hhj");
		doctor.setNumberProfessionalCard("Pediatría");
		doctor.setYearsExperience(1);
		doctor.setAttentionStartTime(8.0);
		doctor.setAttentionEndTime(14.5);
	}

	@Test
	@DisplayName("Test to save a Doctor from Repository.")
	void testSaveDoctor() {
		// when - acción o comportamiento a probar.
		Doctor doctorSaved = doctorRepository.save(doctor);

		// then - verificación de la salida esperada.
		assertThat(doctorSaved).isNotNull();
		assertThat(doctorSaved.getDoctorsName()).isEqualTo(doctor.getDoctorsName());
		assertThat(doctorSaved.getSpecialty()).isEqualTo(doctor.getSpecialty());
	}

	@Test
	@DisplayName("Test to List Doctors from Repository.")
	void testListDoctors() {
		// given - condición previa o configuración. Crear el paciente
		doctorRepository.save(doctor);

		// when - acción o comportamiento a probar.
		List<Doctor> doctorList = (List<Doctor>) doctorRepository.findAll();

		// then - verificación de la salida esperada.
		assertThat(doctorList).isNotNull();
		assertThat(doctorList.size()).isEqualTo(1);
	}

	@Test
	@DisplayName("Test to get a doctor by id from Repository.")
	void testGetDoctorById() {
		// given - condición previa o configuración.
		doctorRepository.save(doctor);

		// when - acción o comportamiento a probar.
		Doctor doctorDB = doctorRepository.findById(doctor.getIdDoctor()).get();

		// then - verificación de la salida esperada.
		assertThat(doctorDB).isNotNull();
		assertThat(doctor.getDoctorsName()).isEqualTo(doctorDB.getDoctorsName());		
	}

	@Test
	@DisplayName("Test to update a doctor from Repository.")
	void testUpdateDoctor() {
		// given - condición previa o configuración.
		doctor.setSpecialty("Neurología");
		doctor.setYearsExperience(2);

		// when - acción o comportamiento a probar.
		Doctor doctorUpdated = doctorRepository.save(doctor);

		// then - verificación de la salida esperada.
		assertThat(doctorUpdated).isNotNull();
		assertThat(doctorUpdated.getSpecialty()).isEqualTo(doctor.getSpecialty());
	}

	@Test
	@DisplayName("Test to delete a doctor by id from Repository.")
	void testDeleteDoctorById() {
		// given - condición previa o configuración.
		doctorRepository.save(doctor);

		// when - acción o comportamiento a probar.
		doctorRepository.deleteById(doctor.getDoctorsName());
		Optional<Doctor> doctorOptional = doctorRepository.findById(doctor.getIdDoctor());

		// then - verificación de la salida esperada.
		assertThat(doctorOptional).isEmpty();
	}

}
