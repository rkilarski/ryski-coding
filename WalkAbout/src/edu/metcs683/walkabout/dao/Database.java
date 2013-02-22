package edu.metcs683.walkabout.dao;

import java.util.List;

public interface Database<T> {

	public T load(long id);
	public void save(T object);
	public List<T> getList(long id);
	
}
