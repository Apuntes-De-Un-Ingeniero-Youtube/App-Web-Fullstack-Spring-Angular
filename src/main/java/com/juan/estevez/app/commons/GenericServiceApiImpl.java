package com.juan.estevez.app.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public abstract class GenericServiceApiImpl<T, L extends Serializable> implements IGenericServiceApi<T, L> {

	public abstract CrudRepository<T, L> getRepository();
	
	@Override
	public T save(T entity) {
		return getRepository().save(entity);
	}

	@Override
	public T update(T entity) {
		return getRepository().save(entity);
	}

	@Override
	public void delete(L id) {
		Optional<T> obj = getRepository().findById(id);
		if (obj.isPresent()) {
			getRepository().deleteById(id);
		}
	}

	@Override
	public T get(L id) {
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
