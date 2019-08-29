package com.phamminhtri.hd_wallpaper.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.phamminhtri.hd_wallpaper.R;


public class SplashAct extends AppCompatActivity {
    private ImageView imgSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideStatusBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgSplash = (ImageView) findViewById(R.id.img_splash);
        loadImage();

        startAct();
    }

    private void startAct() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashAct.this, HomeAct.class));
                finish();
            }
        }, 3000);
    }

    private void loadImage() {
        Glide.with(this).load(R.drawable.splash_screen).into(imgSplash);
    }

    private void hideStatusBar() {
       getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
