package com.phamminhtri.hd_wallpaper.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.phamminhtri.hd_wallpaper.R;
import com.phamminhtri.hd_wallpaper.adapter.HomeAdapter;
import com.phamminhtri.hd_wallpaper.model.AlbumModel;
import com.phamminhtri.hd_wallpaper.service.ApiService;
import com.phamminhtri.hd_wallpaper.service.DataClient;
import com.phamminhtri.hd_wallpaper.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFrag extends Fragment {
    private RecyclerView rvHome;
    private ArrayList<AlbumModel> albumModels;
    private HomeAdapter homeAdapter;

    public HomeFrag() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        init();
        getdata();
        return view;
    }

    private void init() {
        albumModels = new ArrayList<>();
        homeAdapter = new HomeAdapter(albumModels, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvHome.setLayoutManager(linearLayoutManager);
        rvHome.setAdapter(homeAdapter);
    }

    private void getdata() {
        DataClient dataClient = ApiService.getData();
        dataClient.listAlbum().enqueue(new Callback<List<AlbumModel>>() {
            @Override
            public void onResponse(Call<List<AlbumModel>> call, Response<List<AlbumModel>> response) {
                //Xử lý dữ liệu trả về
                Log.e("AAA", "onResponsghjge: " + response.body());
                albumModels.addAll(response.body());
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AlbumModel>> call, Throwable t) {
                Toast.makeText(getContext(), "lỗi" + t, Toast.LENGTH_LONG);
                //Xử lý lỗi

            }
        });
    }

}
