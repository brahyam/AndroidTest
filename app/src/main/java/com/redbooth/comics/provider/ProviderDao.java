package com.redbooth.comics.provider;

import com.redbooth.comics.model.JsonComicListResponse;
import com.redbooth.comics.model.MarvelJsonResponse;

import java.util.Map;

/**
 * Created by Brahyam on 19/11/2016.
 */

public interface ProviderDao {

    /**
     * Enqueue request to obtain new list of comics from service.
     * @param characterId Integer that represents the character ID which comics belong to.
     * @param query Map<String,<String> containing URL query values to include in the request.
     * @param callBackListener listener that is going to be notified when request is done/failed.
     */
    void getComicsFromCharacter(int characterId,Map<String,String> query,retrofit2.Callback<MarvelJsonResponse> callBackListener);

}
