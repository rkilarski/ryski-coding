package com.guitariffic.dao.fakesheet.adapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.guitariffic.dao.fakesheet.BaseAdapter;

/**
 * Class that takes care of reading and writing serializable files.
 * 
 * @author ryszardkilarski
 * 
 */
public class SerialAdapter extends BaseAdapter
{
	/**
	 * Method to read a serialized file and re-inflate into an object.
	 * 
	 * @param file
	 * @return Object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object openFile(File file) throws IOException, ClassNotFoundException
	{
		Object o;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		fis = new FileInputStream(file);
		in = new ObjectInputStream(fis);
		o = in.readObject();
		in.close();
		return o;
	}

	/**
	 * Method to write a serializable object to a file.
	 * 
	 * @param o
	 * @param file
	 * @throws IOException
	 */
	public void saveFile(Object o, File file) throws IOException
	{
		// Serialize to normal file
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		fos = new FileOutputStream(file);
		out = new ObjectOutputStream(fos);
		out.writeObject(o);
		out.close();
	}

	/**
	 * Method to serialize an object to a byte array.
	 * 
	 * @param object
	 * @return byte[]
	 * @throws IOException
	 */
	public byte[] toByteArray(Object object) throws IOException
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeObject(object);
		byte[] byteArray = bos.toByteArray();

		out.close();
		bos.close();
		return byteArray;
	}

	/**
	 * Method to unserialize a byte array into an object.
	 * 
	 * @param byteArray
	 * @return Object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object fromByteArray(byte[] byteArray) throws IOException, ClassNotFoundException
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
		ObjectInput in = new ObjectInputStream(bis);
		Object object = in.readObject();

		bis.close();
		in.close();
		return object;
	}
}
