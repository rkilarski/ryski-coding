package edu.metcs683.walkabout.dao;

import java.util.List;

public interface Database<T> {

	public T get(long id);
	public void insert(T object);
	public List<T> getList(long id);
	public void delete(long id);
	public void update(T object);
	
}
