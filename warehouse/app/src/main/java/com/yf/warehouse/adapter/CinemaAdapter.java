package com.yf.warehouse.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yf.warehouse.R;
import com.yf.warehouse.data.Cinema;
import com.yf.warehouse.interfaces.ItemClickCallback;

import java.util.List;

/**
 * @author cjq
 */
public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.CinemaViewHolder> {
    private Context context;
    private ItemClickCallback itemClickCallBack;
    private List<Cinema> cinemaList;
    public CinemaAdapter(Context context, ItemClickCallback itemClickCallBack, List<Cinema> cinemaList){
        this.context = context;
        this.itemClickCallBack = itemClickCallBack;
        this.cinemaList = cinemaList;
    }
    @NonNull
    @Override
    public CinemaAdapter.CinemaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cinema_item,null);
        return new CinemaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaAdapter.CinemaViewHolder holder, final int position) {
        Cinema cinema = cinemaList.get(position);
        holder.imageView.setImageResource(cinema.image);
        holder.name.setText(cinema.name);
        holder.description.setText(cinema.decrip);
        holder.root_view.setOnClickListener(new View.OnClickListener() {
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
        return cinemaList == null?0:cinemaList.size();
    }

    static class CinemaViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout root_view;
        private ImageView imageView;
        private TextView name;
        private TextView description;
        public CinemaViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_view);
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            root_view = view.findViewById(R.id.root_view);
        }
    }
}
