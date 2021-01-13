package com.base.AtoZMaintenanceApp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.base.AtoZMaintenanceApp.R;
import com.bumptech.glide.Glide;

import java.util.List;

import com.base.AtoZMaintenanceApp.imagePicker.MediaFile;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private Context context;
    private List<MediaFile> imagesFiles;

    public ImagesAdapter(Context context, List<MediaFile> imagesFiles) {
        this.context = context;
        this.imagesFiles = imagesFiles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ViewHolder(inflater.inflate(R.layout.view_image, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RequestOptions myOptions = new RequestOptions()
                .override(400, 400)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.card_bg);

        Glide.with(context)
                .asBitmap()
                .apply(myOptions)
                .load(imagesFiles.get(position).getFile())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imagesFiles.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }


    }
}