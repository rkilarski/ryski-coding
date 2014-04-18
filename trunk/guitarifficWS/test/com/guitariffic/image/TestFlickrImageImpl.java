package com.guitariffic.image;

import java.io.IOException;

import org.junit.Test;

public class TestFlickrImageImpl {

    @Test
    public void testFlickrCall() {
        FlickrImageImpl flickr = new FlickrImageImpl();
        try {
            flickr.callFlickr("elton john");
            //flickr.getImages("elton john");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
