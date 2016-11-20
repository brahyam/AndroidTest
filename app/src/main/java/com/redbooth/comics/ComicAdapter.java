package com.redbooth.comics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redbooth.comics.model.Comic;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

class ComicAdapter extends RecyclerView.Adapter<ComicViewHolder> {
    private List<Comic> comics;

    ComicAdapter() {
        this.comics = Collections.emptyList();
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(com.redbooth.comics.R.layout.comic_list_content, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComicViewHolder holder, int position) {
        final Comic comic = comics.get(position);
        holder.item = comic;
        holder.title.setText(comic.getTitle());
        Log.d(getClass().getSimpleName(),"onBindViewHolder - thumbnail url:"+comic.getThumbnailURL());
        Picasso.with(holder.itemView.getContext())
                .load(comic.getThumbnailURL())
                .fit()
                .centerInside()
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (comics != null) {
            count = comics.size();
        }
        return count;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }
}
