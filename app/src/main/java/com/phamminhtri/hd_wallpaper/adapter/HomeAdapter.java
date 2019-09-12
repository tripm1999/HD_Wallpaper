package com.phamminhtri.hd_wallpaper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.phamminhtri.hd_wallpaper.R;
import com.phamminhtri.hd_wallpaper.model.AlbumModel;
import com.phamminhtri.hd_wallpaper.service.ApiService;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<AlbumModel> modelArrayList;
    private final Context context;
    private final OnItemClickListener listener;

    public HomeAdapter(ArrayList<AlbumModel> modelArrayList, Context context, OnItemClickListener listener) {
        this.modelArrayList = modelArrayList;
        this.context = context;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(AlbumModel albumModel);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new itemHome(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AlbumModel albumModel = modelArrayList.get(position);
        ((itemHome) holder).Container(albumModel);

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    class itemHome extends RecyclerView.ViewHolder {
        private final TextView tvTitleHome;
        private final Button btnMoreHome;
        private final ImageView imgHome;
        private final TextView tvView;
        private final TextView tvLove;

        itemHome(@NonNull View itemView) {
            super(itemView);
            tvTitleHome = itemView.findViewById(R.id.tv_title_home);
            btnMoreHome = itemView.findViewById(R.id.btn_more_home);
            imgHome = itemView.findViewById(R.id.img_home);
            tvView = itemView.findViewById(R.id.tv_view);
            tvLove = itemView.findViewById(R.id.tv_love);
        }

        void Container(final AlbumModel albumModel) {
            String img = ApiService.Base_Url + albumModel.getImage();
            String view = albumModel.getView() + "";
            String love = albumModel.getLove() + "";

            tvTitleHome.setText(albumModel.getName());
            Glide.with(context).load(img).into(imgHome);
            tvView.setText(view);
            tvLove.setText(love);
            btnMoreHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(albumModel);

                }
            });

        }
    }
}
