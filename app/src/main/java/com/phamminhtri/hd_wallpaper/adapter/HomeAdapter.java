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
import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<AlbumModel> modelArrayList;
    private Context context;

    public HomeAdapter(ArrayList<AlbumModel> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
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
   ((itemHome) holder).Container(albumModel.getName(), albumModel.getImage(), albumModel.getView()+"", albumModel.getLove()+"", albumModel.getId());

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class itemHome extends RecyclerView.ViewHolder {
        private TextView tvTitleHome;
        private Button btnMoreHome;
        private ImageView imgHome;
        private TextView tvView;
        private TextView tvLove;

        public itemHome(@NonNull View itemView) {
            super(itemView);
            tvTitleHome = itemView.findViewById(R.id.tv_title_home);
            btnMoreHome = itemView.findViewById(R.id.btn_more_home);
            imgHome = itemView.findViewById(R.id.img_home);
            tvView = itemView.findViewById(R.id.tv_view);
            tvLove = itemView.findViewById(R.id.tv_love);
        }

        public void Container(String name, String image, String view, String love, String idalbum) {
            tvTitleHome.setText(name);
//            Glide.with(context).load(image).into(imgHome);
            tvView.setText(view);
            tvLove.setText(love);
            btnMoreHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}
