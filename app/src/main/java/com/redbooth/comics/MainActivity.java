package com.redbooth.comics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.redbooth.comics.dao.ComicDao;
import com.redbooth.comics.dao.impl.ComicDaoSqlImpl;
import com.redbooth.comics.model.Comic;
import com.redbooth.comics.model.MarvelJsonResponse;
import com.redbooth.comics.provider.ProviderDao;
import com.redbooth.comics.provider.impl.ProviderDaoMarvelImpl;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<MarvelJsonResponse> {

    private RecyclerView mList;
    private ComicAdapter comicAdapter;
    private List<Comic> comics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.redbooth.comics.R.layout.activity_main);

        //Initialize data
        ComicDao comicDao = new ComicDaoSqlImpl();
        comics = comicDao.getAllComics();
        Log.d(getClass().getSimpleName(),"onCreate - retrieved "+comics.size()+" from db.");

        //Initialize comics list
        mList = (RecyclerView) findViewById(com.redbooth.comics.R.id.comic_list);
        comicAdapter = new ComicAdapter();
        mList.setAdapter(comicAdapter);
        comicAdapter.setComics(comics);
        comicAdapter.notifyDataSetChanged();

        //Try to update comics list
        ProviderDao providerDao = new ProviderDaoMarvelImpl();
        providerDao.getComicsFromCharacter(1010733,new HashMap<String, String>(),this);
    }

    @Override
    public void onResponse(Call<MarvelJsonResponse> call, Response<MarvelJsonResponse> response) {
        if(response.code() == 200){
            // Update Comics in DB
            ComicDao comicDao = new ComicDaoSqlImpl();
            comicDao.updateComics(response.body().getComicList());

            // Update comics in view.
            comics = comicDao.getAllComics();
            if(comicAdapter == null){
                comicAdapter = new ComicAdapter();
                mList.setAdapter(comicAdapter);
            }
            Toast.makeText(this,getString(R.string.updated_comics),Toast.LENGTH_SHORT).show();
            comicAdapter.setComics(comics);
            comicAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<MarvelJsonResponse> call, Throwable t) {
        Log.d(getClass().getSimpleName(),"onFailure - message:"+t.getMessage());
        Toast.makeText(this,getString(R.string.update_error),Toast.LENGTH_SHORT).show();
    }
}
