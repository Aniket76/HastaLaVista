package com.aniketvishal.hastalavista;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.aniketvishal.hastalavista.Data.Cat2Data;
import com.aniketvishal.hastalavista.adapter.MainAdapter;
import com.aniketvishal.hastalavista.adapter.MainCategoryAdapter;
import com.aniketvishal.hastalavista.model.MainCategoryModel;
import com.aniketvishal.hastalavista.model.Mainmodel;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * Created by aniketvishal on 30/04/17.
 */

public class BrideFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private LinearLayout mShare,mMoreApps,mRateUs;

    public static BrideFragment newInstance() {
        BrideFragment brideFragment=new BrideFragment();
        return brideFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bridefragmentlayout,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String fragment = getClass().getSimpleName();

        mRecyclerView = (RecyclerView)getActivity().findViewById(R.id.cat2_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MainCategoryAdapter(Cat2Data.getCategoryData(),getContext(), fragment);

        ScaleInAnimationAdapter alphaAdapter = new ScaleInAnimationAdapter(mAdapter);
        alphaAdapter.setDuration(1000);
        alphaAdapter.setInterpolator(new OvershootInterpolator());
        alphaAdapter.setFirstOnly(false);

        mRecyclerView.setAdapter(alphaAdapter);


        mShare = (LinearLayout) getActivity().findViewById(R.id.cat2_image1);
        mRateUs = (LinearLayout) getActivity().findViewById(R.id.cat2_image2);
        mMoreApps = (LinearLayout)getActivity().findViewById(R.id.cat2_image);

        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "https://play.google.com/store/apps/details?id=com.aniketvishal.hastalavista";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hasta-La-Vista: Farewell to 2k15 Batch");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

        mMoreApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("http://play.google.com/store/apps/dev?id=8793989410816601174")));

            }
        });

        mRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("https://play.google.com/store/apps/details?id=com.aniketvishal.hastalavista")));

            }
        });

    }

}

