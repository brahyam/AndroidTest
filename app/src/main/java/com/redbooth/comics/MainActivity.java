package com.redbooth.comics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.redbooth.comics.model.JsonComicListResponse;
import com.redbooth.comics.model.MarvelJsonResponse;
import com.redbooth.comics.provider.ProviderDao;
import com.redbooth.comics.provider.impl.ProviderDaoMarvelImpl;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements Callback<MarvelJsonResponse> {

    private RecyclerView mList;
    private Map<String, String> mMap;
    private Retrofit.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // on create
        super.onCreate(savedInstanceState);
        setContentView(com.redbooth.comics.R.layout.activity_main);

        ProviderDao providerDao = new ProviderDaoMarvelImpl();
        providerDao.getComicsFromCharacter(1010733,new HashMap<String, String>(),this);



//        // set recyclerview
//        mList = (RecyclerView) findViewById(com.redbooth.comics.R.id.comic_list);
//
//        // Create comic adapter
//        final ComicAdapter comicAdapter = new ComicAdapter();
//
//        // set adapter
//        mList.setAdapter(comicAdapter);
//
//        String timestamp = String.valueOf(System.currentTimeMillis()); // replace here with correct values
//        String privateKey = "44500734e389e5be35cf88021b54f28718e49987"; // replace here with correct values
//        String publicKey = "ee2a8e9560b1402675b41cbb4a6d22a6"; // replace here with correct values
//        String hash = "";
//        try {
//            MessageDigest md = MessageDigest.getInstance("getMD5Hash");
//            hash = new BigInteger(1, md.digest(String.format("%server%server%server", timestamp, privateKey, publicKey).getBytes())).toString(16);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        // Create hashmap
//        mMap = new HashMap<>();
//        mMap.put("ts", timestamp);
//        mMap.put("apikey", publicKey);
//        mMap.put("hash", hash);
//
//        // update marvel
//
//        // Create builder
//        builder = new Retrofit.Builder().baseUrl("http://gateway.marvel.com/v1/public/").addConverterFactory(GsonConverterFactory.create());
//
//        // call marvel updating. Don't forget to call it
//        marvel_updating(comicAdapter, mMap, builder);
    }

    /**
     * Method marvel_updating
     * Class MainActivity
     * <p>
     * author Unknown
     * modified by Unknown
     * <p>
     * This method receives a ComicAdapter, a Map and a Builder. Returns nothing.
     * This method updates marvel
     * This method generates a retrofit object and calls amazingcomics. It then calls
     * enqueue. It then notifies data set changed
     *
     * @param comicAdapter ComicAdapter a
     * @param stringMap    Map mMap
     * @param builder      Builder builder
     */
    private void marvel_updating(final ComicAdapter comicAdapter, Map<String, String> stringMap, Retrofit.Builder builder) {
        // update
//
//        // build retrofit
//        retrofit = builder.build();
//
//        // create server
//        server = retrofit.create(Server.class);
//
//        // retrieve amazing comics
//        marvelCall = server.amazingspiderman(1010733, stringMap);
//
//        // enqueue amazing comics call
//        marvelCall.enqueue(new Callback<Marvel>() {
//            @Override
//            public void onResponse(Call<Marvel> c, Response<Marvel> r) {
//                // Everything is ok
//                if (r.code() == 200) {
//
//                    // set
//                    comicAdapter.setComics(r.body().data.results);
//
//                    // notify data set changed
//                    comicAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Marvel> call, Throwable t) {
//                //TODO do something here
//            }
//        });

    }

    @Override
    public void onResponse(Call<MarvelJsonResponse> call, Response<MarvelJsonResponse> response) {
        Log.d(getClass().getSimpleName(),"onResponse - message:"+response.message());
        Log.d(getClass().getSimpleName(),"onResponse - body:"+response.raw().toString());
        Log.d(getClass().getSimpleName(),"onResponse - code:"+response.code());
        if(response.body() != null){
            Log.d(getClass().getSimpleName(),"onResponse - items:"+response.body().getComicList().size());
        }
    }

    @Override
    public void onFailure(Call<MarvelJsonResponse> call, Throwable t) {
        Log.d(getClass().getSimpleName(),"onFailure - message:"+t.getMessage());
    }
}
