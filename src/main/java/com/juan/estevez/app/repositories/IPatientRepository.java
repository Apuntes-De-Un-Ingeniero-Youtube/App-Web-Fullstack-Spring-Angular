package com.juan.estevez.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.juan.estevez.app.entities.Patient;

/**
 * Interface para manejar el repositorio CRUD con JPA del tipo Patient.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Repository
public interface IPatientRepository extends CrudRepository<Patient, String> {

}
