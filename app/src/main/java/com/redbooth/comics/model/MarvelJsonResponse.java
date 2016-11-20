package com.redbooth.comics.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brahyam on 19/11/2016.
 */

public class MarvelJsonResponse extends JsonComicListResponse {

    public Data data;

    public static class Data {
        public List<Comic> results = new ArrayList<>();
    }

    @Override
    public List<Comic> getComicList() {
        return data.results;
    }
}
