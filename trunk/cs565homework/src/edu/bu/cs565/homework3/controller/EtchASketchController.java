package edu.bu.cs565.homework3.controller;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.bu.cs565.homework3.model.EtchASketchCanvas;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * This is the Etch-A-Sketch controller class.
 */
public class EtchASketchController {

	public void saveImage(EtchASketchCanvas canvas, File file) {
		try {
			if (file != null) {
				if (getFileExtension(file.getName()).equals("etch")) {
					saveFile(canvas, file);
				} else {
					writeImageToFile(canvas.getCanvasImage(), file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * Load an image into a canvas.
	 * 
	 * @return EtchASketchCanvas - The canvas from a file.
	 */
	public EtchASketchCanvas openImage(File file) {
		EtchASketchCanvas canvas = null;
		try {
			if (file != null) {
				canvas = (EtchASketchCanvas) openFile(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return canvas;
	}

	/**
	 * This method shakes a given frame horizontally.
	 * 
	 * @param frame
	 *            - The frame to shake.
	 */
	public void shakeWindow(JFrame frame) {
		// the horizontal displacement
		int deltaX = 20;
		final int shakeDuration = 100;

		// make sure the browser support the moveBy method
		for (int shakeCounter = 0; shakeCounter < shakeDuration; shakeCounter++) {
			Point p = frame.getLocation();

			// shake left
			if ((shakeCounter % 4) == 0) {
				p.setLocation(p.getX() - deltaX, p.getY());
				frame.setLocation(p);
			}
			// shake right
			else if ((shakeCounter % 4) == 2) {
				p.setLocation(p.getX() + deltaX, p.getY());
				frame.setLocation(p);
			}

			frame.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// Just don't sleep on an exception.
			}

			// speed up or slow down every X cycles
			if ((shakeCounter % 30) == 0) {
				// speed up halfway
				if (shakeCounter < shakeDuration / 2) {
					deltaX++;
				}
				// slow down after halfway of the duration
				else {
					deltaX--;
				}
			}
		}
	}

	/**
	 * Return the file extension for a given filename.
	 * 
	 * @param fileName
	 * @return
	 */
	private String getFileExtension(String fileName) {
		int mid = fileName.lastIndexOf(".");
		return fileName.substring(mid + 1, fileName.length());
	}

	/**
	 * Prompt the user for a file.
	 * 
	 * @param showOpenDialog
	 * @return
	 */
	public File promptForFile(boolean showOpenDialog) {
		int returnValue;
		File file = null;
		final JFileChooser fileChooser = new JFileChooser();

		if (showOpenDialog) {
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
					"Etch A Sketch File", "etch"));

			returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
			}
		} else {
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
					"JPeg File", "jpg"));
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
					"Etch A Sketch File", "etch"));

			returnValue = fileChooser.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {

				String[] extensions = ((FileNameExtensionFilter) fileChooser
						.getFileFilter()).getExtensions();

				if (!getFileExtension(fileChooser.getSelectedFile().getName())
						.equals(extensions[0])) {
					// Add extension if it didn't exist already
					file = new File(fileChooser.getSelectedFile()
							.getAbsoluteFile() + "." + extensions[0]);
				} else {
					file = fileChooser.getSelectedFile();
				}
			}
		}
		return file;
	}

	/**
	 * Outputs the image to a jpeg file.
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	private void writeImageToFile(BufferedImage image, File file)
			throws IOException {
		// write to file
		Iterator<ImageWriter> writers = ImageIO
				.getImageWritersByFormatName("jpeg");
		ImageWriter writer = (ImageWriter) writers.next();
		if (writer == null) {
			throw new RuntimeException("JPeg not supported.");
		}

		ImageOutputStream out = ImageIO.createImageOutputStream(file);
		writer.setOutput(out);
		writer.write(image);
		out.close(); // close flushes buffer
	}

	/**
	 * Method to read a serialized file and re-inflate into an object.
	 * 
	 * @param file
	 * @return Object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private Object openFile(File file) throws IOException,
			ClassNotFoundException {
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
	private void saveFile(Object o, File file) throws IOException {
		// Serialize to normal file
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		fos = new FileOutputStream(file);
		out = new ObjectOutputStream(fos);
		out.writeObject(o);
		out.close();
	}
}
