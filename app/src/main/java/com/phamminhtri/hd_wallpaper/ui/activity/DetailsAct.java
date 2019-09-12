package com.phamminhtri.hd_wallpaper.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.phamminhtri.hd_wallpaper.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsAct extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgImage;
    private FloatingActionButton fbSave;
    private FloatingActionButton fbSetWallpaper;
    private FloatingActionButton fbShare;
    String linkimage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        loadimg();
    }

    private void loadimg() {
        Intent intent = this.getIntent();

            linkimage = intent.getExtras().getString("linkImg");
            Glide.with(this).load(linkimage).into(imgImage);
    }

    private void init() {
        imgImage = findViewById(R.id.img_image);
        fbSave = findViewById(R.id.fb_save);
        fbSetWallpaper = findViewById(R.id.fb_set_wallpaper);
        fbShare = findViewById(R.id.fb_share);

        fbSave.setOnClickListener(this);
        fbSetWallpaper.setOnClickListener(this);
        fbShare.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fb_save:
                initPermission();
                Glide.with(this).asBitmap().load(linkimage).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        saveImage(resource);
                    }
                });
                break;
            case R.id.fb_set_wallpaper:
                Glide.with(this).asBitmap().load(linkimage).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        saveWallpaper(resource);
                    }
                });
                break;
            case R.id.fb_share:
                break;

        }

    }

    private void saveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/HdWallpaper");
        myDir.mkdirs();

        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fname = "wallpaper" + timeStamp + ".jpg";

        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Intent mediaScanIntent = new Intent(
                        Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(myDir);                //out is your output file
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);
            } else {
                sendBroadcast(new Intent(
                        Intent.ACTION_MEDIA_MOUNTED,
                        Uri.parse("file://"
                                + myDir)));
            }

            out.close();
            Toast.makeText(this, "Download Success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Download Error: " + e, Toast.LENGTH_SHORT).show();
            Log.e("Tri", "Download Error: " + e);
        }
    }

    private void saveWallpaper(Bitmap finalBitmap) {
        WallpaperManager m = WallpaperManager.getInstance(this);

        try {
            m.setBitmap(finalBitmap);
            Toast.makeText(this, "Wallpaper Set Successfully!!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Setting WallPaper Failed!!", Toast.LENGTH_SHORT).show();
        }

    }

    public void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //Permisson don't granted
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Log.d("Doan", "onRequestPermissionsResult: success ");
                }
                // Permisson don't granted and dont show dialog again.
                else {
                    Log.d("Doan", "onRequestPermissionsResult: Failed");
                }
                //Register permission
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }
}