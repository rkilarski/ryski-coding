package edu.bu.cs565.homework3.controller;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * This is the Etch-A-Sketch controller class.
 */
public class EtchASketchController {

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

	public void saveImage(BufferedImage image) {
		File file = promptForFile(true, false);
		if (file != null) {
			try {
				writeImageToFile(image, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
	 * Prompt the user for a file.
	 * 
	 * @param showOpenDialog
	 * @return
	 */
	private File promptForFile(boolean showOpenDialog, boolean saveAsFlag) {
		int returnValue;
		File file = null;
		final JFileChooser fileChooser = new JFileChooser();

		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
				"JPeg File", "jpg"));

		returnValue = fileChooser.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {

			String[] extensions = ((FileNameExtensionFilter) fileChooser
					.getFileFilter()).getExtensions();

			if (!this.getFileExtension(fileChooser.getSelectedFile().getName())
					.equals(extensions[0])) {
				// Add extension if it didn't exist already
				file = new File(fileChooser.getSelectedFile().getAbsoluteFile()
						+ "." + extensions[0]);
			} else {
				file = fileChooser.getSelectedFile();
			}
		}
		return file;
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
}
