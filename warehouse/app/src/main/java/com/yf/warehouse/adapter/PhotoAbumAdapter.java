package com.yf.warehouse.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yf.warehouse.R;
import com.yf.warehouse.interfaces.ItemClickCallback;

import java.util.List;

/**
 * adapter如果没显示数据
 * 1.recyclerView是否没设置布局管理器recyclerView.setLayoutManager(gridLayoutManager);
 * 2.确认getItemCount 是否返回0
 */
public class PhotoAbumAdapter extends RecyclerView.Adapter<PhotoAbumAdapter.PhotoViewHolder> {
    private Context context;
    private ItemClickCallback itemClickCallBack;
    private List<Integer> imageList;

    public PhotoAbumAdapter(Context context, ItemClickCallback itemClickCallBack, List<Integer> imageList){
        this.context = context;
        this.itemClickCallBack = itemClickCallBack;
        this.imageList = imageList;
    }
    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.photoabum_item,null);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, final int position) {
        holder.imageView.setImageResource(imageList.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickCallBack != null){
                    itemClickCallBack.itemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList==null?0:imageList.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public PhotoViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_view);
        }
    }
}
