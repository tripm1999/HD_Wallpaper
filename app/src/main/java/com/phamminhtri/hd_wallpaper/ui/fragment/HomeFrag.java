package com.phamminhtri.hd_wallpaper.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phamminhtri.hd_wallpaper.R;
import com.phamminhtri.hd_wallpaper.adapter.HomeAdapter;
import com.phamminhtri.hd_wallpaper.model.AlbumModel;
import com.phamminhtri.hd_wallpaper.service.ApiService;
import com.phamminhtri.hd_wallpaper.service.DataClient;

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
        rvHome = view.findViewById(R.id.rv_home);
        init();
        getdata();
        return view;
    }

    private void init() {
        albumModels = new ArrayList<>();
        homeAdapter = new HomeAdapter(albumModels, getContext(), new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AlbumModel albumModel) {
                FragmentManager fragmentManager = getFragmentManager();
                ImageFrag imageFrag = new ImageFrag();
                //gửi giữ liệu sang frag bên
                Bundle args = new Bundle();
                args.putString("idalbum", albumModel.getId());
                args.putString("titlee", "album: " + albumModel.getName());
                imageFrag.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frame_frag, imageFrag);
                //addToBackStack(null) -  cho fag vào hàng đợi để có thể back trở lại
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvHome.setLayoutManager(linearLayoutManager);
        rvHome.setHasFixedSize(true);
        rvHome.setAdapter(homeAdapter);
    }

    private void getdata() {
        DataClient dataClient = ApiService.getData();
        dataClient.listAlbum().enqueue(new Callback<List<AlbumModel>>() {
            @Override
            public void onResponse(Call<List<AlbumModel>> call, Response<List<AlbumModel>> response) {
                assert response.body() != null;
                albumModels.addAll(response.body());
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AlbumModel>> call, Throwable t) {
                Log.d("AAG", "onFailure: " + t);
                //Xử lý lỗi

            }
        });
    }

}
