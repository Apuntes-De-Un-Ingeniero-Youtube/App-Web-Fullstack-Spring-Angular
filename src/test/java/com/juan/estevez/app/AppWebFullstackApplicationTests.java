package com.juan.estevez.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.juan.estevez.app.controllers.DoctorRestController;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class AppWebFullstackApplicationTests {

	private DoctorRestController doctorRestController;
	private TestRestTemplate testRestTemplate;

	@Autowired
	public AppWebFullstackApplicationTests(DoctorRestController doctorRestController, TestRestTemplate testRestTemplate) {
		this.doctorRestController = doctorRestController;
		this.testRestTemplate = testRestTemplate;
	}

	@Test
	 void contextLoads() {
		assertThat(doctorRestController).isNotNull();
		assertThat(testRestTemplate).isNotNull();
	}

}
