package com.aniketvishal.hastalavista.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aniketvishal.hastalavista.R;
import com.aniketvishal.hastalavista.model.Mainmodel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aniketvishal on 30/04/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<Mainmodel> mainmodels;
    private Context context;
    private Onitemclicked onitemclicked;

    public MainAdapter(ArrayList<Mainmodel> mainmodels, Context context, Onitemclicked onitemclicked){
        this.mainmodels=mainmodels;
        this.context=context;
        this.onitemclicked=onitemclicked;
    }
    public interface  Onitemclicked{
        public void onimageclicked(int pos);

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.maincard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(mainmodels.get(position).getPicture()).fit()
                .into(holder.main_image);

    }

    @Override
    public int getItemCount() {
        return mainmodels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.main_image)
        ImageView main_image;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
            main_image.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos=getAdapterPosition();
            onitemclicked.onimageclicked(pos);
        }
    }
}
