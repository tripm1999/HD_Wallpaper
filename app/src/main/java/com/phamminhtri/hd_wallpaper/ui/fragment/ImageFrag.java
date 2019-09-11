package com.phamminhtri.hd_wallpaper.ui.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phamminhtri.hd_wallpaper.R;
import com.phamminhtri.hd_wallpaper.adapter.ImageAdapter;
import com.phamminhtri.hd_wallpaper.model.CategoryModel;
import com.phamminhtri.hd_wallpaper.model.ImageModel;
import com.phamminhtri.hd_wallpaper.service.ApiService;
import com.phamminhtri.hd_wallpaper.service.DataClient;
import com.phamminhtri.hd_wallpaper.ui.activity.DetailsAct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageFrag extends Fragment {
    private RecyclerView rv_Image;
    private ImageAdapter imageAdapter;
    private ArrayList<ImageModel> imageList;
    private String title = "";
    private String idalbum = "";

    public ImageFrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        rv_Image = view.findViewById(R.id.rv_image);
        Bundle args = getArguments();
        if (args != null) {
            idalbum = args.getString("idalbum");
            title = args.getString("titlee");
            Log.e("AAG", "idcate: " + idalbum);
            title = args.getString("titlee");
        }
        init();
        getDataa();
        return view;
    }

    private void init() {
        getActivity().setTitle(title);

        LinearLayoutManager layoutManagerl = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        imageList = new ArrayList<>();
        //khởi tạo adapter image và set onclick cho item.
        imageAdapter = new ImageAdapter(imageList, getContext(), new ImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ImageModel imageModel) {
                String linkimg = "http://192.168.1.5:3000/"+ imageModel.getImage();
                Intent intent = new Intent(getContext(), DetailsAct.class);
                intent.putExtra("linkImg", linkimg);
                startActivity(intent);
            }
        });
        rv_Image.setLayoutManager(layoutManagerl);
        rv_Image.setAdapter(imageAdapter);
    }

    private void getDataa() {
        //lấy dữ liệu từ sever
        DataClient dataClient = ApiService.getData();
        dataClient.listImg(idalbum).enqueue(new Callback<List<ImageModel>>() {
            @Override
            public void onResponse(Call<List<ImageModel>> call, Response<List<ImageModel>> response) {
                assert response.body() != null;
                imageList.addAll(response.body());
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ImageModel>> call, Throwable t) {
                Log.d("huy", "onFailure: " + t);
            }
        });
    }


}
