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
import com.phamminhtri.hd_wallpaper.adapter.CategoryAdapter;
import com.phamminhtri.hd_wallpaper.model.CategoryModel;
import com.phamminhtri.hd_wallpaper.service.ApiService;
import com.phamminhtri.hd_wallpaper.service.DataClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFrag extends Fragment {
    private RecyclerView rv_category;
    private CategoryAdapter categoryAdapter;
    private ArrayList<CategoryModel> categorylist;

    public CategoryFrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        rv_category = view.findViewById(R.id.rv_cate);
        init();
        getData();
        return view;

    }

    private void init() {
        LinearLayoutManager layoutManagerl = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        categorylist = new ArrayList<>();
        //khởi tạo adapter cate và set onclick cho item.
        categoryAdapter = new CategoryAdapter(categorylist, getContext(), new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CategoryModel categoryModel) {
                FragmentManager fragmentManager = getFragmentManager();
                AlbumInCateFrag albumInCateFrag = new AlbumInCateFrag();
                //gửi giữ liệu sang frag bên
                Bundle args = new Bundle();
                args.putString("idcate", categoryModel.getId());
                args.putString("titlee", "category: " + categoryModel.getTitle());
                albumInCateFrag.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frame_frag, albumInCateFrag, "");
                //addToBackStack(null) -  cho fag vào hàng đợi để có thể back trở lại
                fragmentTransaction.addToBackStack("Category");
                fragmentTransaction.commit();
            }

        });
        rv_category.setLayoutManager(layoutManagerl);
        rv_category.setHasFixedSize(true);
        rv_category.setAdapter(categoryAdapter);
    }

    private void getData() {
        //lấy dữ liệu từ sever
        DataClient dataClient = ApiService.getData();
        dataClient.listCategory().enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                assert response.body() != null;
                categorylist.addAll(response.body());
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                Log.d("huy", "onFailure: " + t);

            }
        });
    }


}
