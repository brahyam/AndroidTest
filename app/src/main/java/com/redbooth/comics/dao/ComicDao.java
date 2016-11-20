package com.redbooth.comics.dao;

import com.redbooth.comics.model.Comic;

import java.util.List;

/**
 * Created by Brahyam on 17/11/2016.
 */

public interface ComicDao {

    /**
     * Returns a list of all comics in DB ordered by title
     * @return list of all comics contained in DB
     */
    List<Comic> getAllComics();

    /**
     * Clears DB content and inserts a list of comics.
     * @param comics list of comics to be inserted
     */
    void updateComics(List<Comic> comics);

}
