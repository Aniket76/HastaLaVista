package com.aniketvishal.hastalavista;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aniketvishal.hastalavista.adapter.MainAdapter;
import com.aniketvishal.hastalavista.model.Mainmodel;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.app_bar_product)
    AppBarLayout appBarLayout;
    @BindView(R.id.wedding_text)
    TextView weddingtext;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.wedding_viewpager)
    ViewPager viewPager;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingtoolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title_toptext)
    TextView title_toptext;

    Typeface typeface;
    ArrayList<Mainmodel> mainmodels=new ArrayList<>();
    MainAdapter mainAdapter;
    private String count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");

        String toyBornTime = "2018-05-05 16:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {

            Date oldDate = dateFormat.parse(toyBornTime);
            System.out.println(oldDate);

            Date currentDate = new Date();

            long diff = oldDate.getTime() - currentDate.getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            setCountDown(days,hours,minutes);

            if (oldDate.after(currentDate)) {

                Log.e("oldDate", "is previous date");
                Log.e("Difference: ", " seconds: " + seconds + " minutes: " + minutes
                        + " hours: " + hours + " days: " + days);

            }

            // Log.e("toyBornTime", "" + toyBornTime);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        setToptext();
        initializeRecycler();
    }

    private void setToptext() {
        typeface=Typeface.createFromAsset(getAssets(),"Satisfy-Regular.ttf");
        title_toptext.setTypeface(typeface);

    }

    private void initializeRecycler() {
        recyclerView.setHasFixedSize(true);
        mainAdapter=new MainAdapter(mainmodels, this, new MainAdapter.Onitemclicked() {
            @Override
            public void onimageclicked(int pos) {
                Intent intent=new Intent(getApplicationContext(),Screenshot_Activity.class);
                intent.putExtra(CONSTANT.textimage,mainmodels.get(pos).getPicture());
                intent.putExtra("Image","");
                startActivity(intent);
            }
        });




        recyclerView.setAdapter(mainAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        SnapHelper snapHelper=new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerView);

        prepareData();




    }

    private void prepareData() {
        mainmodels.add(new Mainmodel(R.drawable.heading));
        mainmodels.add(new Mainmodel(R.drawable.m1));
        mainmodels.add(new Mainmodel(R.drawable.m2));
        mainmodels.add(new Mainmodel(R.drawable.m3));
        mainmodels.add(new Mainmodel(R.drawable.m4));
        mainmodels.add(new Mainmodel(R.drawable.m5));
        mainmodels.add(new Mainmodel(R.drawable.m6));
        mainmodels.add(new Mainmodel(R.drawable.m7));
        mainmodels.add(new Mainmodel(R.drawable.m8));
        mainmodels.add(new Mainmodel(R.drawable.m9));
        mainmodels.add(new Mainmodel(R.drawable.m10));
        mainmodels.add(new Mainmodel(R.drawable.m11));
        mainmodels.add(new Mainmodel(R.drawable.m12));
        mainmodels.add(new Mainmodel(R.drawable.m13));
        mainmodels.add(new Mainmodel(R.drawable.m14));
        mainmodels.add(new Mainmodel(R.drawable.m15));
        mainmodels.add(new Mainmodel(R.drawable.m16));
        mainmodels.add(new Mainmodel(R.drawable.m17));
        mainmodels.add(new Mainmodel(R.drawable.m18));
        mainmodels.add(new Mainmodel(R.drawable.heading1));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(IntroFragment.newInstance(),"Coders");
        adapter.addFragment(BrideFragment.newInstance(),"Rebels");
        adapter.addFragment(GroomFragment.newInstance(),"Back Benchers");
        adapter.addFragment(Cat4Fragment.newInstance(),"Teacher's Favorites");
        viewPager.setAdapter(adapter);
        // viewPager.setPageTransformer(true,new CubeInTransformer());

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    private void setCountDown(long day, long hour, long min){

        String d = Long.toString(day);
        String h = Long.toString((hour%24));
        String m = Long.toString((min%60));

        final AlertDialog.Builder mBulider = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.count_layout, null);

        final TextView mCount = (TextView)mView.findViewById(R.id.cl_count);
        mCount.setText(d+"d : "+h+"h : "+m+"m");
        final Button mOkay = (Button) mView.findViewById(R.id.cl_ok);

        mBulider.setView(mView);
        final AlertDialog dialog = mBulider.create();

        mOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
