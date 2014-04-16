package com.guitariffic.image;

import java.util.List;

public abstract class ImageImpl {

    public static ImageImpl newImageImpl(String src) {
        if (src == null) {
            throw new IllegalArgumentException("src may not be null.");
        }
        switch (src) {
            case "Flickr":
                return new FlickrImageImpl();
            default:
                throw new RuntimeException("Unknown source: " + src);
        }
    }
    
    public abstract List<String> getImages(String search);
}
