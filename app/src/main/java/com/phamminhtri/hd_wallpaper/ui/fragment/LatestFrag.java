package com.phamminhtri.hd_wallpaper.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phamminhtri.hd_wallpaper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestFrag extends Fragment {


    public LatestFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest, container, false);
    }

}
