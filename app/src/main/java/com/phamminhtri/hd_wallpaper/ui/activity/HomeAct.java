package com.phamminhtri.hd_wallpaper.ui.activity;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;
import com.phamminhtri.hd_wallpaper.R;
import com.phamminhtri.hd_wallpaper.ui.fragment.CategoryFrag;
import com.phamminhtri.hd_wallpaper.ui.fragment.HomeFrag;
import com.phamminhtri.hd_wallpaper.ui.fragment.LatestFrag;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class HomeAct extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FrameLayout frameFrag;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        showTooBar();
        init();
        setFrag();


    }

    private void setFrag() {
        HomeFrag homeFrag = new HomeFrag();
        fragmentTransaction = fragmentManager.beginTransaction().add(R.id.frame_frag, homeFrag);
        fragmentTransaction.commit();

    }

    private void init() {
        frameFrag = (FrameLayout) findViewById(R.id.frame_frag);
        fragmentManager = getSupportFragmentManager();

    }

    private void showTooBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_latest:
                setTitle(R.string.title_latest);
                LatestFrag latestFrag = new LatestFrag();
                fragmentTransaction.replace(R.id.frame_frag, latestFrag);
                break;

            case R.id.nav_category:
                setTitle(R.string.title_category);
                CategoryFrag categoryFrag = new CategoryFrag();
                fragmentTransaction.replace(R.id.frame_frag, categoryFrag);
                break;

            case R.id.nav_gifs:
                setTitle(R.string.title_gifs);
                break;
            case R.id.nav_favorite:
                setTitle(R.string.title_frag_favorite);
                break;
            case R.id.nav_rate:
                setTitle(R.string.title_rate);
                break;
            case R.id.nav_more:
                setTitle(R.string.title_more);
                break;
            case R.id.nav_about:
                setTitle(R.string.title_about);
                break;
            case R.id.nav_setting:
                setTitle(R.string.title_setting);
                break;
            case R.id.nav_primary:
                setTitle(R.string.title_primary);
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
