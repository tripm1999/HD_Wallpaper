package com.phamminhtri.hd_wallpaper.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.phamminhtri.hd_wallpaper.R;
import com.phamminhtri.hd_wallpaper.adapter.AlbumincateAdapter;
import com.phamminhtri.hd_wallpaper.model.AlbuminCate;
import com.phamminhtri.hd_wallpaper.service.ApiService;
import com.phamminhtri.hd_wallpaper.service.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumInCateFrag extends Fragment {
    private String idcate = "";
    private String title = "album";
    private ArrayList<AlbuminCate> albuminCates;
    private AlbumincateAdapter adapter;
    private RecyclerView rvAlbumInCate;

    public AlbumInCateFrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_in_cate, container, false);
        rvAlbumInCate = view.findViewById(R.id.rv_album_in_cate);
        Bundle args = getArguments();
        if (args != null) {
            idcate = args.getString("idcate");
            Log.e("AAG", "idcate: " + idcate);
            title = args.getString("titlee");
        }
        init();
        getDataIncate();

        return view;
    }

    private void init() {
        getActivity().setTitle(title);
        albuminCates = new ArrayList<>();
        adapter = new AlbumincateAdapter(albuminCates, getContext(), new AlbumincateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AlbuminCate albuminCate) {
                FragmentManager fragmentManager = getFragmentManager();
                ImageFrag imageFrag = new ImageFrag();
                //gửi giữ liệu sang frag bên
                Bundle args = new Bundle();
                args.putString("idalbum", albuminCate.getId());
                args.putString("titlee", "album: " + albuminCate.getName());
                imageFrag.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frame_frag, imageFrag);
                //addToBackStack(null) -  cho fag vào hàng đợi để có thể back trở lại
                fragmentTransaction.addToBackStack("Album");
                fragmentTransaction.commit();
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        rvAlbumInCate.setLayoutManager(gridLayoutManager);
        rvAlbumInCate.setHasFixedSize(true);
        rvAlbumInCate.setAdapter(adapter);


    }

    private void getDataIncate() {
        DataClient dataClient = ApiService.getData();
        Toast.makeText(getContext(), "idcate " + idcate, Toast.LENGTH_SHORT).show();
        dataClient.listAlbuminCate(idcate).enqueue(new Callback<List<AlbuminCate>>() {
            @Override
            public void onResponse(Call<List<AlbuminCate>> call, Response<List<AlbuminCate>> response) {
//                Xử lý dữ liệu trả về
                Log.e("AAG", "onResponsghjge: " + response.body());
                if (response.body() != null) {
                    albuminCates.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<AlbuminCate>> call, Throwable t) {
                Log.e("AAG", "onFailure: " + t);
            }
        });
    }


}
