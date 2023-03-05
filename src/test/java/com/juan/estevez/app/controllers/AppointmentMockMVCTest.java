package com.juan.estevez.app.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.services.IAppointmentService;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentMockMVCTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IAppointmentService appointmentService;

	@Autowired
	private ObjectMapper objectMapper;

	private Appointment appointment;

	@BeforeEach
	void init() {
		appointment = new Appointment();
		appointment.setIdAppointment(2340);
		appointment.setDoctor("900");
		appointment.setPatient("7892");
		appointment.setDate("2024-12-09");
		appointment.setHour(15);
	}

	@Test
	void testSaveAppointmentController() throws JsonProcessingException, Exception {
		// given
		given(appointmentService.save(any(Appointment.class))).willAnswer((invocation) -> invocation.getArgument(0));

		// when
		ResultActions response = mockMvc.perform(post("/appointment").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(appointment)));

		// then
		response.andExpect(status().isOk()).andExpect(jsonPath("$.doctor", is(appointment.getDoctor())))
				.andExpect(jsonPath("$.patient", is(appointment.getPatient())));
	}

	@Test
	void testListAppointmentsController() throws Exception {
		// given
		List<Appointment> appointmentList = new ArrayList<>();
		appointmentList.add(new Appointment(12, "800", "34", "2023-02-12", 12));
		appointmentList.add(new Appointment(11, "800", "35", "2023-02-12", 11));
		appointmentList.add(new Appointment(10, "800", "35", "2023-02-12", 10));

		given(appointmentService.list()).willReturn(appointmentList);

		// when
		ResultActions response = mockMvc.perform(get("/appointment/findAll"));

		// then
		response.andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(appointmentList.size())));
	}

	@Test
	void testGetAppointmentByIdController() throws Exception {
		// given
		given(appointmentService.get(appointment.getIdAppointment())).willReturn(appointment);

		// when
		ResultActions response = mockMvc.perform(get("/appointment/findById/{idAppointment}", appointment.getIdAppointment()));

		// then
		response.andExpect(status().isOk()).andExpect(jsonPath("$.patient", is(appointment.getPatient())));
	}

	@Test
	void testUpdateAppointmentController() throws JsonProcessingException, Exception {
		// given
		appointment.setPatient("808080");
		appointment.setHour(14);

		given(appointmentService.get(appointment.getIdAppointment())).willReturn(appointment);

		given(appointmentService.update(any(Appointment.class))).willAnswer((invocation) -> invocation.getArgument(0));

		// when
		ResultActions response = mockMvc.perform(put("/appointment").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(appointment)));

		// then
		response.andExpect(status().isOk()).andExpect(jsonPath("$.patient", is(appointment.getPatient())));
	}

	@Test
	void testDeleteAppointmentController() throws Exception {
		// given
		given(appointmentService.get(appointment.getIdAppointment())).willReturn(appointment);

		// when
		ResultActions response = mockMvc
				.perform(delete("/appointment/{idAppointment}", appointment.getIdAppointment()));

		// then
		response.andExpect(status().isOk());
	}

}
