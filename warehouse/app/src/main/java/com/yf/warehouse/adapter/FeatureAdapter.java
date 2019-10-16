package com.yf.warehouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yf.warehouse.R;
import com.yf.warehouse.viewhelper.DemoDetails;

/**
 * @author cjq
 */
public class FeatureAdapter extends BaseAdapter {
    private Context context;
    private DemoDetails[] demos;

    public FeatureAdapter(Context context,DemoDetails[] demoDetails){
        this.context = context;
        demos = demoDetails;
    }

    @Override
    public int getCount() {
        return demos == null?0:demos.length;
    }

    @Override
    public DemoDetails getItem(int position) {
        return demos[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            //首次加载，在可见屏幕范围都需要创建这个convertView,创建完成后，在上下滑动时均不需要再创建
            android.util.Log.d("sssssss","xxxxxxxxxx"+convertView);
            convertView = LayoutInflater.from(context).inflate(R.layout.feature_item,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            android.util.Log.d("sssssss","xxxxxxxxxx----------"+convertView);
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tvTitle.setText(demos[position].titleId);
        viewHolder.tvDescription.setText(demos[position].descriptionId);
        return convertView;
    }

    class ViewHolder {
        TextView tvTitle;
        TextView tvDescription;

        public ViewHolder(View itemView) {
            tvTitle = (TextView)itemView.findViewById(R.id.title);
            tvDescription = itemView.findViewById(R.id.description);
        }
    }
}
