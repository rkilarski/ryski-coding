package com.guitariffic.dbo;

import java.util.List;

public interface DBHelper<T> {
	public void add(T object);

	public void update(T object, String id);

	public void delete(String id);

	public List<T> getList(String search);

	public T get(String id);
}
