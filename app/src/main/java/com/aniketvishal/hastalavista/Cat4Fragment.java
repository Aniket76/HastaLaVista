package com.aniketvishal.hastalavista;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import com.aniketvishal.hastalavista.Data.Cat2Data;
import com.aniketvishal.hastalavista.Data.Cat4Data;
import com.aniketvishal.hastalavista.adapter.MainCategoryAdapter;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cat4Fragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayout mShare,mMoreApps,mRateUs;

    public Cat4Fragment() {
        // Required empty public constructor
    }

    public static Cat4Fragment newInstance() {
        Cat4Fragment fragment =new Cat4Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat4, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String fragment = getClass().getSimpleName();

        mRecyclerView = (RecyclerView)getActivity().findViewById(R.id.cat4_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MainCategoryAdapter(Cat4Data.getCategoryData(),getContext(), fragment);

        ScaleInAnimationAdapter alphaAdapter = new ScaleInAnimationAdapter(mAdapter);
        alphaAdapter.setDuration(1000);
        alphaAdapter.setInterpolator(new OvershootInterpolator());
        alphaAdapter.setFirstOnly(false);

        mRecyclerView.setAdapter(alphaAdapter);


        mShare = (LinearLayout) getActivity().findViewById(R.id.cat4_image1);
        mRateUs = (LinearLayout) getActivity().findViewById(R.id.cat4_image2);
        mMoreApps = (LinearLayout)getActivity().findViewById(R.id.cat4_image);

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
