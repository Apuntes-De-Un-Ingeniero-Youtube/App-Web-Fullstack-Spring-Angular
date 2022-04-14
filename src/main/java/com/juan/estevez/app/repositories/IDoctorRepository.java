package com.juan.estevez.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.juan.estevez.app.entities.Doctor;

/**
 * Interface para manejar el repositorio CRUD con JPA del tipo Doctor.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Repository
public interface IDoctorRepository extends CrudRepository<Doctor, String> {

}
