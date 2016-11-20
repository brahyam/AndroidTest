package com.redbooth.comics.model;

/**
 * Created by Brahyam on 17/11/2016.
 */

public class Comic {
    private String title;
    private Thumbnail thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailURL() {
        return this.getThumbnail().getPath()+"."+this.getThumbnail().getExtension();
    }
}