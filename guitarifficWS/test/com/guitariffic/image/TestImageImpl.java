/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.image;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class TestImageImpl {

	@Test
	public void testGetImages() {
		ImageImpl imageImpl = ImageImpl.newImageImpl("flickr");
		try {
			List<String> urls = imageImpl.getImages("elton john");
			assertTrue("No images found.", urls.size() > 0);
			for (String url : urls) {
				System.out.println(url);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
