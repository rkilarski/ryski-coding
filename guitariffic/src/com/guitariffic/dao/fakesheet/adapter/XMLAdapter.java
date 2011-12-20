package com.guitariffic.dao.fakesheet.adapter;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.guitariffic.dao.fakesheet.BaseAdapter;

/**
 * Class that takes care of reading and writing XML files.
 * 
 * @author ryszardkilarski
 * 
 */
public class XMLAdapter extends BaseAdapter
{
	/**
	 * Method to read a serialized XML file and re-inflate into an object.
	 * 
	 * @param file
	 * @return Object
	 * @throws IOException
	 */
	public Object openFile(File file) throws IOException
	{
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
		Object o = decoder.readObject();
		decoder.close();
		return o;
	}

	/**
	 * Method to write a serializable object to an XML file.
	 * 
	 * @param o
	 * @param file
	 * @throws IOException
	 */
	public void saveFile(Object o, File file) throws IOException
	{
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
		encoder.writeObject(o);
		encoder.close();
	}
}
