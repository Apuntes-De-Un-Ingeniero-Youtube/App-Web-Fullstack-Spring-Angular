package com.juan.estevez.app.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juan.estevez.app.entities.Patient;
import com.juan.estevez.app.services.IPatientService;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientMockMVCIT {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IPatientService patientService;

	@Autowired
	private ObjectMapper objectMapper;
	
	private Patient patient;
	
	@BeforeEach
	void init() {
		patient = new Patient();
		patient.setIdPatient("9000");
		patient.setName("Julio");
		patient.setIdType("CC");
		patient.setDateOfBirth("1987-10-12");
		patient.setEps("EPS X");
		patient.setHistoryClinic("OK");
	}

	@Test
	void testSavePatient() throws Exception {
		// given
		given(patientService.save(any(Patient.class)))
			.willAnswer((invocation) -> invocation.getArgument(0));

		// when
		ResultActions response = mockMvc.perform(post("/patient")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(patient)));

		// then
		response.andDo(print()).andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is((patient.getName()))))
			.andExpect(jsonPath("$.eps",is(patient.getEps())));
	}
	
	@Test
	void testListPatients() throws Exception {
		// given
		List<Patient> patientList = new ArrayList<>();
		patientList.add(new Patient("9001", "Patient1", "CC", "1998-10-12", "EPS 1", "OK"));
		patientList.add(new Patient("9002", "Patient2", "CC", "1998-10-12", "EPS 2", "OK"));
		patientList.add(new Patient("9003", "Patient3", "CC", "1998-10-12", "EPS 3", "OK"));
		
		given(patientService.list()).willReturn(patientList);
		
		// when
		ResultActions response = mockMvc.perform(get("/patient/findAll"));
		
		// then
		response.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.size()", is(patientList.size())));
	}
	
	@Test
	void testGetPatientById() throws Exception {
		// given		
		given(patientService.get(patient.getIdPatient())).willReturn(patient);
		
		// when
		ResultActions response = mockMvc.perform(get("/patient/findById/{idPatient}", patient.getIdPatient()));
		
		// then
		response.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(patient.getName())))
				.andExpect(jsonPath("$.eps", is(patient.getEps())));

	}
	
	@Test
	void testUpdatePatient() throws Exception {
		// given
		patient.setName("Julio Updated");
		patient.setEps("EPS Y");
		
		given(patientService.get(patient.getIdPatient())).willReturn(patient);
		given(patientService.update(any(Patient.class))).willAnswer((invocation) -> invocation.getArgument(0));
		
		// when
		ResultActions response = mockMvc.perform(put("/patient").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(patient)));
		
		// then
		response.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(patient.getName())));
	}

	@Test
	void testDeletePatient() throws Exception {
		// given
		given(patientService.get(patient.getIdPatient())).willReturn(patient);
		
		// when
		ResultActions response = mockMvc.perform(delete("/patient/{idPatient}", patient.getIdPatient()));
		
		// then
		response.andExpect(status().isOk());
	}
}
