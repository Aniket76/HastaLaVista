package com.aniketvishal.hastalavista.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aniketvishal.hastalavista.R;

/**
 * Created by aniketvishal on 05/02/18.
 */

public class MainCategoryViewHolder extends RecyclerView.ViewHolder {

    public TextView mCategory;
    public ImageView mBackgroung;
    public RelativeLayout mMainCont;

    public MainCategoryViewHolder(View itemView) {
        super(itemView);

        mCategory = (TextView)itemView.findViewById(R.id.category_list_Category);
        mBackgroung = (ImageView)itemView.findViewById(R.id.category_list_background);
        mMainCont = (RelativeLayout)itemView.findViewById(R.id.category_list);

    }

}
