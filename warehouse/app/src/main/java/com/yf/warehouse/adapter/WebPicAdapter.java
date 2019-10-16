package com.yf.warehouse.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yf.warehouse.R;
import com.yf.warehouse.data.WebImagesBean;
import com.yf.warehouse.data.WebPicData;
import com.yf.warehouse.interfaces.ItemClickCallback;
import com.yf.warehouse.utils.Constant;

import java.util.List;

/**
 * adapter如果没显示数据
 * 1.recyclerView是否没设置布局管理器recyclerView.setLayoutManager(gridLayoutManager);
 * 2.确认getItemCount 是否返回0
 */
public class WebPicAdapter extends RecyclerView.Adapter<WebPicAdapter.WebPicViewHolder> {
    private Context context;
    private ItemClickCallback itemClickCallBack;
    private WebPicData webPicData;

    public WebPicAdapter(Context context, ItemClickCallback itemClickCallBack,
                         WebPicData webPicData) {
        this.context = context;
        this.itemClickCallBack = itemClickCallBack;
        this.webPicData = webPicData;
    }

    @NonNull
    @Override
    public WebPicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.webpic_item, null);
        return new WebPicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WebPicViewHolder holder, final int position) {
        holder.bindData(webPicData.getImages().get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickCallBack != null) {
                    itemClickCallBack.itemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return webPicData == null || webPicData.getImages() == null ? 0 :
                webPicData.getImages().size();
    }

    static class WebPicViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView description;

        public WebPicViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_view);
            description = view.findViewById(R.id.description);
        }

        public void bindData(WebImagesBean webImagesBean) {
            description.setText(webImagesBean.getCopyright());
            Glide.with(imageView).load(Constant.API_HOST + webImagesBean.getUrl()).into(imageView);
        }
    }
}
