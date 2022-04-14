package com.juan.estevez.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.juan.estevez.app.entities.Appointment;

/**
 * Interface para manejar el repositorio CRUD con JPA del tipo Appointment.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Repository
public interface IAppointmentRepository extends CrudRepository<Appointment, Integer>{

}
