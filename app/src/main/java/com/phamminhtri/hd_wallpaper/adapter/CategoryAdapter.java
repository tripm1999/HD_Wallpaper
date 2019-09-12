package com.phamminhtri.hd_wallpaper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.phamminhtri.hd_wallpaper.R;
import com.phamminhtri.hd_wallpaper.model.CategoryModel;
import com.phamminhtri.hd_wallpaper.service.ApiService;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<CategoryModel> modelArrayList;
    private Context context;
    private OnItemClickListener listener;

    public CategoryAdapter(ArrayList<CategoryModel> modelArrayList, Context context, OnItemClickListener listener) {
        this.modelArrayList = modelArrayList;
        this.context = context;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(CategoryModel categoryModel);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new itemCategory(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryModel categoryModel = modelArrayList.get(position);
        ((itemCategory) holder).Container(categoryModel, listener);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    private class itemCategory extends RecyclerView.ViewHolder {
        private ImageView imgCate;
        private TextView tvTitleCate;
        private TextView tvSizeCate;

        private itemCategory(@NonNull View itemView) {
            super(itemView);
            imgCate = itemView.findViewById(R.id.img_cate);
            tvTitleCate = itemView.findViewById(R.id.tv_title_cate);
            tvSizeCate = itemView.findViewById(R.id.tv_size_cate);
        }

        private void Container(final CategoryModel categoryModel, final OnItemClickListener listener) {
            String catesize = "( " + categoryModel.getCountalbum() + " )";
            Glide.with(context).load(ApiService.Base_Url + categoryModel.getImage()).into(imgCate);
            tvTitleCate.setText(categoryModel.getTitle());
            tvSizeCate.setText(catesize);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(categoryModel);
                }
            });
        }
    }
}
