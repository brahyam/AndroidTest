package com.redbooth.comics.provider.impl;

import com.redbooth.comics.model.MarvelJsonResponse;
import com.redbooth.comics.provider.ProviderDao;
import com.redbooth.comics.provider.ServiceFactory;
import com.redbooth.comics.utils.WebUtils;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Brahyam on 19/11/2016.
 */

public class ProviderDaoMarvelImpl implements ProviderDao {

    private String privateKey = "44500734e389e5be35cf88021b54f28718e49987";
    private String publicKey = "ee2a8e9560b1402675b41cbb4a6d22a6";

    @Override
    public void getComicsFromCharacter(int characterId, Map<String, String> query, final Callback<MarvelJsonResponse> callBackListener) {

        /**
         * Specific Marvel API security settings.
         */
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash = WebUtils.getMD5Hash(timestamp+privateKey+publicKey);

        // Add Marvel API security settings to query values.
        query.put("ts", timestamp);
        query.put("apikey", publicKey);
        query.put("hash", hash);

        MarvelService service = ServiceFactory.createRetrofitService(MarvelService.class,MarvelService.SERVICE_ENDPOINT);
        Call<MarvelJsonResponse> call = service.getComicsFromCharacter(characterId,query);
        call.enqueue(callBackListener);
    }

    public interface MarvelService{
        String SERVICE_ENDPOINT = "http://gateway.marvel.com/v1/public/";

        /**
         * Get all the comics from a specific character.
         */
        @GET("characters/{characterId}/comics")
        Call<MarvelJsonResponse> getComicsFromCharacter(@Path("characterId") int characterId, @QueryMap Map<String, String> digest);
    }
}
