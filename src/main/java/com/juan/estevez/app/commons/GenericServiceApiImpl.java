package com.juan.estevez.app.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Clase abstracta y genérica que implementa la interface IGenericServiceApi
 * para modificar registros en la base de datos utilizando Data JPa.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 * @param <T> Tipo de repositorio a manejar (Appointment, Doctor, Patient).
 * @param <L> Tipo de dato de la llave primaria del repositorio a manejar.
 */
//service
@Service
public abstract class GenericServiceApiImpl<T, ID extends Serializable> implements IGenericServiceApi<T, ID> {

	public abstract CrudRepository<T, ID> getRepository();

	@Override
	public T save(T entity) {
		return getRepository().save(entity);
	}

	@Override
	public T update(T entity) {
		return getRepository().save(entity);
	}

	@Override
	public void delete(ID id) {
		Optional<T> obj = getRepository().findById(id);
		if (obj.isPresent()) {
			getRepository().deleteById(id);
		}
	}

	@Override
	public T get(ID id) {
		Optional<T> obj = getRepository().findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	public List<T> list() {
		List<T> returnList = new ArrayList<>();
		getRepository().findAll().forEach(returnList::add);
		return returnList;
	}

}
