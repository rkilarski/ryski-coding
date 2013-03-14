package edu.metcs683.walkabout.dao;

import java.util.List;

public interface Database<T> {
	public static final String DB_NAME = "edu.metcs683.walkabout";
	public static final int DB_VERSION = 3;

	
	public T get(long id);
	public void insert(T object);
	public List<T> getAll(long id);
	public void delete(long id);
	public void update(T object);
	
}
