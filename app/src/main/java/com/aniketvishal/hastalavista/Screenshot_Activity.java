package com.aniketvishal.hastalavista;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by gautam on 25/2/17.
 */

public class Screenshot_Activity extends AppCompatActivity {
    @BindView(R.id.screenshot_image)
    TouchImageView screenshotimage;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;

    GradientDrawable gd;

    int image;
    int imagestring;
    String imageValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.screenshot_activity);
        ButterKnife.bind(this);
        imagestring = getIntent().getIntExtra(CONSTANT.textimage, 0);
        imageValue = getIntent().getStringExtra("Image");
        initilizeFullScreen();

    }

    private void initilizeFullScreen() {

        if (!(imageValue.equals(""))){

            Picasso.with(getApplicationContext()).load(imageValue).fit().centerInside()
                    .placeholder(R.drawable.gradient_mini)
                    .into(screenshotimage);
            // screenshotimage.setBackgroundResource(R.drawable.gradient_blue);

        } else {

        Picasso.with(getApplicationContext()).load(imagestring).fit().centerInside()
                .placeholder(R.drawable.gradient_mini)
                .into(screenshotimage);
        // screenshotimage.setBackgroundResource(R.drawable.gradient_blue);

        }

    }

}


