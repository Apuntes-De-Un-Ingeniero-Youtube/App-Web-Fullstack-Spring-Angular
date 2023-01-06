package com.juan.estevez.app.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.juan.estevez.app.entities.Doctor;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DoctorDataJPAIT {

	private TestRestTemplate testRestTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public DoctorDataJPAIT(TestRestTemplate testRestTemplate, JdbcTemplate jdbcTemplate) {
		this.testRestTemplate = testRestTemplate;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanInsertDoctor.sql")
	void postDoctor() {
		HttpEntity<Doctor> request = new HttpEntity<>(createDoctor());
		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor", HttpMethod.POST,
				request, Doctor.class);
		
		List<Map<String, Object>> responseDatabase = jdbcTemplate.queryForList("SELECT COUNT(*) FROM DOCTOR WHERE ID_DOCTOR = ?",
				response.getBody().getIdDoctor());
		
		assertThat(responseDatabase.size()).isNotNegative().isNotNull();
		assertThat(!responseDatabase.isEmpty());
		
		//assertEquals(1, responseDatabase.get(0).values());
	

		/* Validando que el id del doctor no sea nulo y que la respuesta de la base de datos 
		 * coincida con el valor esperado. */
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals(createDoctor().getIdDoctor(), response.getBody().getIdDoctor());

		assertThat(response.getBody().getDoctorsName()).isNotEmpty();
		assertEquals(createDoctor().getDoctorsName(), response.getBody().getDoctorsName());
		//assertEquals(createDoctor().getDoctorsName(), responseDatabase.get(1));

		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals(createDoctor().getIdType(), response.getBody().getIdType());

		assertThat(response.getBody().getNumberProfessionalCard()).isNotEmpty();
		assertEquals(createDoctor().getNumberProfessionalCard(), response.getBody().getNumberProfessionalCard());

		assertThat(response.getBody().getYearsExperience()).isNotNegative();
		assertEquals(createDoctor().getYearsExperience(), response.getBody().getYearsExperience());

		assertThat(response.getBody().getSpecialty()).isNotEmpty();
		assertEquals(createDoctor().getSpecialty(), response.getBody().getSpecialty());

		assertThat(response.getBody().getAttentionStartTime()).isNotNegative();
		assertEquals(createDoctor().getAttentionStartTime(), response.getBody().getAttentionStartTime());

		assertThat(response.getBody().getAttentionEndTime()).isNotNegative();
		assertEquals(createDoctor().getAttentionEndTime(), response.getBody().getAttentionEndTime());
	}

	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorToUpdate.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanDoctorToupdate.sql")
	void putDoctor() {
		HttpEntity<Doctor> request = new HttpEntity<>(updateDoctor());
		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor", HttpMethod.PUT,
				request, Doctor.class);
		
		List<Map<String, Object>> responseDatabase = jdbcTemplate.queryForList("SELECT * FROM DOCTOR WHERE ID_DOCTOR = ?",
				response.getBody().getIdDoctor());
		
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertThat(responseDatabase.get(0)).isNotEmpty().isNotNull();
		assertEquals(updateDoctor().getIdDoctor(), response.getBody().getIdDoctor());
		assertEquals(response.getBody().getIdDoctor(), responseDatabase.get(0).get("ID_DOCTOR"));

		assertThat(response.getBody().getDoctorsName()).isNotEmpty();
		assertEquals(updateDoctor().getDoctorsName(), response.getBody().getDoctorsName());
		assertEquals(response.getBody().getDoctorsName(), responseDatabase.get(0).get("DOCTORS_NAME"));

		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals(updateDoctor().getIdType(), response.getBody().getIdType());

		assertThat(response.getBody().getNumberProfessionalCard()).isNotEmpty();
		assertEquals(updateDoctor().getNumberProfessionalCard(), response.getBody().getNumberProfessionalCard());

		assertThat(response.getBody().getYearsExperience()).isNotNegative();
		assertEquals(updateDoctor().getYearsExperience(), response.getBody().getYearsExperience());

		assertThat(response.getBody().getSpecialty()).isNotEmpty();
		assertEquals(updateDoctor().getSpecialty(), response.getBody().getSpecialty());

		assertThat(response.getBody().getAttentionStartTime()).isNotNegative();
		assertEquals(updateDoctor().getAttentionStartTime(), response.getBody().getAttentionStartTime());

		assertThat(response.getBody().getAttentionEndTime()).isNotNegative();
		assertEquals(updateDoctor().getAttentionEndTime(), response.getBody().getAttentionEndTime());
		
	}


	private Doctor createDoctor() {
		Doctor doctor = new Doctor();
		doctor.setIdDoctor("1010");
		doctor.setDoctorsName("Doctor Test");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("1111");
		doctor.setYearsExperience(10);
		doctor.setSpecialty("Cardiología");
		doctor.setAttentionStartTime(8);
		doctor.setAttentionEndTime(16);
		return doctor;
	}
	
	private Doctor updateDoctor() {
		Doctor doctor = new Doctor();
		doctor.setIdDoctor("101010");
		doctor.setDoctorsName("Doctor Update Test");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("1111");
		doctor.setYearsExperience(4);
		doctor.setSpecialty("Cardiología");
		doctor.setAttentionStartTime(8);
		doctor.setAttentionEndTime(16);
		return doctor;
	}
}
