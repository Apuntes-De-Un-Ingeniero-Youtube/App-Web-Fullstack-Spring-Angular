package com.juan.estevez.app.services;

import com.juan.estevez.app.commons.IGenericServiceApi;
import com.juan.estevez.app.entities.Patient;

/**
 * Interface para manipular los métodos CRUD del tipo Patient. Esta
 * interface extiende de clase genérica IGenericServiceApi y pasamos como
 * parámetro el tipo de dato Patient junto con el tipo de dato de su llave
 * primaria.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
public interface IPatientService extends IGenericServiceApi<Patient, String> {

}
