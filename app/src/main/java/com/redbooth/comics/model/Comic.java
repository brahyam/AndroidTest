package com.redbooth.comics.model;

/**
 * Created by Brahyam on 17/11/2016.
 */

public class Comic {
    private String title;
    private Thumbnail thumbnail;

    public String getThumbnailURL() {
        return thumbnail.getPath()+ "." + thumbnail.getExtension();
    }

    public String getTitle() {
        return title;
    }
}