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
import com.phamminhtri.hd_wallpaper.model.AlbuminCate;
import com.phamminhtri.hd_wallpaper.service.ApiService;

import java.util.ArrayList;

public class AlbumincateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<AlbuminCate> modeList;
    private final Context context;
    private final OnItemClickListener onItemClickListener;

    public AlbumincateAdapter(ArrayList<AlbuminCate> modeList, Context context, OnItemClickListener onItemClickListener) {
        this.modeList = modeList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(AlbuminCate albuminCate);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_albumincate, parent, false);
        return new itemAlbuminCate(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AlbuminCate albuminCate = modeList.get(position);
        ((itemAlbuminCate) holder).Container(albuminCate, onItemClickListener);

    }

    @Override
    public int getItemCount() {
        return modeList.size();
    }

    class itemAlbuminCate extends RecyclerView.ViewHolder {
        private final ImageView imgAlbum;
        private final TextView tvView;
        private final TextView tvLove;
        private final TextView tvName;


        itemAlbuminCate(@NonNull View itemView) {
            super(itemView);
            imgAlbum = itemView.findViewById(R.id.img_album);
            tvView = itemView.findViewById(R.id.tv_view);
            tvLove = itemView.findViewById(R.id.tv_love);
            tvName = itemView.findViewById(R.id.tv_name);

        }

        void Container(final AlbuminCate albuminCate, final OnItemClickListener onItemClickListener) {
            Glide.with(context).load(ApiService.Base_Url + albuminCate.getImage()).into(imgAlbum);
            String name = (albuminCate.getName() + "");
            String love = (albuminCate.getLove() + "");
            String view = (albuminCate.getView() + "");

            tvName.setText(name);
            tvLove.setText(love);
            tvView.setText(view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(albuminCate);
                }
            });
        }
    }
}
