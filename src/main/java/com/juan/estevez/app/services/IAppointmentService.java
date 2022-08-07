package com.juan.estevez.app.services;

import com.juan.estevez.app.commons.IGenericServiceApi;
import com.juan.estevez.app.entities.Appointment;

/**
 * Interface para manipular los métodos CRUD del tipo Appointment. Esta
 * interface extiende de clase genérica IGenericServiceApi y pasamos como
 * parámetro el tipo de dato Appointment junto con el tipo de dato de su llave
 * primaria.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
public interface IAppointmentService extends IGenericServiceApi<Appointment, Integer> {

}
