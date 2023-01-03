package com.juan.estevez.app;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configura el objeto para mapear objetos de tipo Patient a PatientDTO y viceversa
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Configuration
public class AppWebFullstackApplicationConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
