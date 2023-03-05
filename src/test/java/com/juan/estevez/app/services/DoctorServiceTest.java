package com.juan.estevez.app.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.repositories.IDoctorRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class DoctorServiceTest {

	@Autowired
	private IDoctorService doctorService;
	
	@MockBean
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
	void testSaveDoctor() throws Exception {
		// given
		given(doctorRepository.save(any(Doctor.class))).willReturn(doctor);

		// when
		Doctor response = doctorService.save(doctor);

		// then
		assertThat(response).isNotNull();
		assertThat(response).isEqualTo(doctor);
	}
}
