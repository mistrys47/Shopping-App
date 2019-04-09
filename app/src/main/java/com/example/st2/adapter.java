package com.example.st2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;
public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    private List<x> moviesList;
    private  OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(x item);
    }
    public adapter(List<x> items, OnItemClickListener listener) {
        this.moviesList = items;
        this.listener = listener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView t1;
        public ImageView i1;
        public MyViewHolder(View view) {
            super(view);

            t1 = (TextView) view.findViewById(R.id.textView);
            i1=(ImageView)view.findViewById(R.id.photo);
        }
        public void bind(final x item, final OnItemClickListener listener) {
            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    x k1=moviesList.get(position);
                    Intent i1=new Intent(v.getContext(),Main4Activity.class);
                    i1.putExtra("id",k1.getId());
                    v.getContext().startActivity(i1);
                }
            });
        }
    }
    public adapter (List<x> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main3, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        x movie = moviesList.get(position);
        holder.t1.setText(movie.getName());
        Uri u=Uri.parse(movie.getImg());
        Picasso.get().load(u).into(holder.i1);
        holder.bind(movie,listener);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}
