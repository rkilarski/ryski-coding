/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.image;

import java.util.List;

import org.apache.axis2.AxisFault;

public abstract class ImageImpl {

	public static ImageImpl newImageImpl(String src) {
		if (src == null) {
			throw new IllegalArgumentException("src may not be null.");
		}
		switch (src) {
			case "flickr":
				return new FlickrImageImpl();
            case "google":
                return new GoogleImageImpl();
			default:
				throw new RuntimeException("Unknown source: " + src);
		}
	}

	public abstract List<String> getImages(String search) throws AxisFault;
}
