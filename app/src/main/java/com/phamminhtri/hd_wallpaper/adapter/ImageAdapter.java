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
import com.phamminhtri.hd_wallpaper.model.ImageModel;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<ImageModel> arrayList;
    private final Context context;
    private final OnItemClickListener onItemClickListener;

    public ImageAdapter(ArrayList<ImageModel> arrayList, Context context, OnItemClickListener onItemClickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(ImageModel imageModel);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new itemImage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageModel imageModel = arrayList.get(position);
        ((itemImage) holder).Container(imageModel, onItemClickListener);

    }

    private class itemImage extends RecyclerView.ViewHolder {
        private ImageView imgImage;
        private TextView tvView;
        private TextView tvLove;

        private itemImage(@NonNull View itemView) {
            super(itemView);

            imgImage = itemView.findViewById(R.id.img_image);
            tvView = itemView.findViewById(R.id.tv_view);
            tvLove = itemView.findViewById(R.id.tv_love);

        }

        private void Container(final ImageModel imageModel, final OnItemClickListener onItemClickListener) {
            Glide.with(context).load("http://192.168.1.5:3000/" + imageModel.getImage()).into(imgImage);
            tvLove.setText(imageModel.getLove() + "");
            tvView.setText(imageModel.getView() + "");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(imageModel);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
