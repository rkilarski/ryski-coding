package com.guitariffic.model;

/**
 * Base class for every class in the Model package.
 * 
 * @author ryszardkilarski
 * 
 */
public abstract class BaseModel
{
	private boolean isDirty;

	public void setDirty(boolean isDirty)
	{
		this.isDirty = isDirty;
	}

	public boolean isDirty()
	{
		return isDirty;
	}
}
