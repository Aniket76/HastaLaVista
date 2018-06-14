package com.aniketvishal.hastalavista.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aniketvishal.hastalavista.ProfileActivity;
import com.aniketvishal.hastalavista.R;
import com.aniketvishal.hastalavista.model.MainCategoryModel;
import com.aniketvishal.hastalavista.viewholders.MainCategoryViewHolder;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

/**
 * Created by aniketvishal on 05/02/18.
 */

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryViewHolder> {

    private List<MainCategoryModel> mCategoryList;
    private Context context;
    private Activity activity;
    private String fragment;

    public MainCategoryAdapter(List<MainCategoryModel> mWordList, Context context, String fragment) {
        this.mCategoryList = mWordList;
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public MainCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_layout,parent,false);

        return new MainCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainCategoryViewHolder holder, int position) {

        activity = (Activity)context;

        final MainCategoryModel mainCategoryModel = mCategoryList.get(position);

        holder.mCategory.setText(mainCategoryModel.getmMainCategories());

        if (fragment.equals("IntroFragment")){
            holder.mBackgroung.setImageResource(R.drawable.coders);
        }else if (fragment.equals("BrideFragment")){
            holder.mBackgroung.setImageResource(R.drawable.rebels);
        }else if (fragment.equals("GroomFragment")){
            holder.mBackgroung.setImageResource(R.drawable.beckbenchers);
        }else if (fragment.equals("Cat4Fragment")){
            holder.mBackgroung.setImageResource(R.drawable.teachers);
        }

        holder.mMainCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Dexter.withActivity(activity)
                        .withPermissions(
                                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                android.Manifest.permission.INTERNET)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                // check if all permissions are granted
                                if (report.areAllPermissionsGranted()) {
                                    // do you work now

                                    Intent myintent = new Intent(activity, ProfileActivity.class);
                                    myintent.putExtra("Name", mainCategoryModel.getmMainCategories());
                                    context.startActivity(myintent);

                                }

                                // check for permanent denial of any permission
                                if (report.isAnyPermissionPermanentlyDenied()) {
                                    // permission is denied permenantly, navigate user to app settings

                                    Toast.makeText(activity, "Permission Not Granted",Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        })
                        .onSameThread()
                        .check();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }
}
