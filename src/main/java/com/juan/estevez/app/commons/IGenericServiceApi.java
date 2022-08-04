package com.juan.estevez.app.commons;

import java.io.Serializable;
import java.util.List;

/**
 * Interface general para manejar el API y el CRUD de manera genérica (Todos los
 * Entities).
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 * @param <T> Tipo de implementación (Appointment, Doctor, Patient).
 * @param <L> Tipo de dato de la llave primaria de ta tabla.
 */
public interface IGenericServiceApi<T, L extends Serializable> {

	/**
	 * Se encarga de guardar registros en la base de datos.
	 * 
	 * @param entity Tipo de entidad a guardar (Appointment, Doctor, Patient).
	 * @return Un tipo genérico según el repository insertado (Patient, Doctor,
	 *         Appointment).
	 */
	T save(T entity);

	/**
	 * Se encarga de actualizar registros en la base de datos.
	 * 
	 * @param entity Tipo de entidad a actualizar (Appointment, Doctor, Patient).
	 * @return Un tipo genérico según el repository actualizado (Patient, Doctor,
	 *         Appointment).
	 */
	T update(T entity);

	/**
	 * Se encarga de eliminar registros de la base de datos.
	 * 
	 * @param id Llave primaria por la cual se eliminará el registro.
	 */
	void delete(L id);

	/**
	 * Se encarga de obtener registros de la base de datos paramatrizados por un ID
	 * en específico.
	 * 
	 * @param id ID por el cuál se buscará el registro.
	 * @return Un tipo genérico según el repository obtenido (Patient, Doctor,
	 *         Appointment).
	 */
	T get(L id);

	/**
	 * Se encarga de generar un listado de registros obtenidos de la base de datos.
	 * 
	 * @return Lista de los elementos obtenidos directamente de la base de datos.
	 */
	List<T> list();
}
