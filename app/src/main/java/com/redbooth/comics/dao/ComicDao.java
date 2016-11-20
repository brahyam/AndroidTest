package com.redbooth.comics.dao;

import com.redbooth.comics.model.Comic;

import java.util.List;

/**
 * Created by Brahyam on 17/11/2016.
 */

public interface ComicDao {

    List<Comic> getAllComics();

    void updateComics(List<Comic> comics);

}
