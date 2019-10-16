package com.yf.warehouse.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yf.warehouse.R;
import com.yf.warehouse.data.AppBean;
import com.yf.warehouse.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjq on 2019/10/15
 * 加载本地应用的方法，线程使用
 * ListView列表滑动事件，布局排版等知识。
 */
public class ListViewTitleGroupActivity extends Activity implements View.OnClickListener {
    private static final int LOAD_APP_FINISH = 1000;
    private ListView listView;
    private TextView tv_sub_title;
    private List<AppBean> allAppInfo;
    private List<AppBean> userAppList;
    private List<AppBean> sysAppList;
    private MyAdapter adapter;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LOAD_APP_FINISH:
                    adapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_titlegroup);
        initView();
        initData();
        initListener();
        loadAppData();
    }
    private void initListener() {
        //为listView 设置滑动的监听
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            /**
             * 当滑动状态发生改变时，调用
             * @param view
             * @param scrollState
             */
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // 打补丁
                if (userAppList == null) {
                    return;
                }
                // 判断屏幕可见的第一个条目是用户应用，还是系统应用
                int firstPosition = listView.getFirstVisiblePosition(); // 可看到的第一个条目的下标
                if (firstPosition < userAppList.size()) { // 用户应用
                    tv_sub_title.setText("用户应用");
                } else {
                    tv_sub_title.setText("系统应用");
                }
            }
        });
    }



    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        tv_sub_title = (TextView) findViewById(R.id.tv_sub_title);
    }

    private Runnable loadingAllApp = new Runnable() {
        @Override
        public void run() {
            allAppInfo = AppUtils.getAllAppInfo(ListViewTitleGroupActivity.this);//获取所有应用集合
            for (AppBean app : allAppInfo) {
                if (app.isSys) {
                    sysAppList.add(app);
                } else {
                    userAppList.add(app);
                }
            }
            mHandler.sendEmptyMessage(LOAD_APP_FINISH);
        }
    };

    private void loadAppData() {
        Toast.makeText(ListViewTitleGroupActivity.this,"正在收集本机所有应用，请稍候...",Toast.LENGTH_LONG).show();
        mHandler.postDelayed(loadingAllApp,200);
    }

    private void initData() {
        userAppList = new ArrayList<AppBean>();//用户应用集合
        sysAppList = new ArrayList<AppBean>();//系统应用集合
        allAppInfo = new ArrayList<>();
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
    /**
     * 有会组和标题的listView 实现思路：
     * 1- 展示一个基本的listview
     * 2- 对listView 进行分组
     * 3- 添加小标题
     * 4- 添加固定的标题
     */
    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // listView 的数据，来自于二个集合
            return userAppList.size() + sysAppList.size();
        }
        /**
         * 根据position 返回数据Bean
         */
        @Override
        public Object getItem(int position) {
            // 根据position 获得对应的数据Bean
            AppBean appInfo;//= allAppInfo.get(position);
            if (position < userAppList.size()) {
                // 从用户集合中取数据
                appInfo = userAppList.get(position);
            } else {
                // 从系统集合中取
                appInfo = sysAppList.get(position - userAppList.size());
            }
            return appInfo;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder vh;
            if (convertView == null) {
                view = getLayoutInflater().inflate(R.layout.list_item_app_manager, null);
                vh = new ViewHolder();
                // 找到子view
                ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_icon_list_item);
                TextView tvName = (TextView) view.findViewById(R.id.tv_app_name_list_item);
                TextView tvSize = (TextView) view.findViewById(R.id.tv_app_size_list_item);
                TextView tvLocation = (TextView) view.findViewById(R.id.tv_app_location_list_item);
                TextView tvSubTitle = (TextView) view.findViewById(R.id.tv_sub_title_list_item);
                tvSubTitle.setVisibility(View.GONE);

                // 将子View打包
                vh.ivIcon = ivIcon;
                vh.tvName = tvName;
                vh.tvSize = tvSize;

                vh.tvLocation = tvLocation;
                vh.tvSubTitle = tvSubTitle;
                // 背在view 的身上
                view.setTag(vh);
            } else {
                view = convertView;
                vh = (ViewHolder) view.getTag();
            }
            // 根据position 获得对应的数据Bean
            AppBean appInfo = (AppBean) getItem(position);
            // 为子view 赋值
            vh.ivIcon.setBackgroundDrawable(appInfo.appIcon);
            vh.tvName.setText(appInfo.appName);
            String sizeStr = Formatter.formatFileSize(ListViewTitleGroupActivity.this, appInfo.appSize);
            vh.tvSize.setText("应用大小:" + sizeStr);
            if (appInfo.isInSd) {
                vh.tvLocation.setText("在SD卡中");
            } else {
                vh.tvLocation.setText("在内部存储中");
            }
            // 处理小标题
            // 第一个用户应用和第一个系统应用，显示标题
            if (position == 0) {
                vh.tvSubTitle.setText("用户应用");
                vh.tvSubTitle.setVisibility(View.VISIBLE);
            } else if (position == userAppList.size()) { // 第一个系统应用
                vh.tvSubTitle.setText("系统应用");
                vh.tvSubTitle.setVisibility(View.VISIBLE);
            } else {
                // 其他情况下，都隐藏标题
                vh.tvSubTitle.setVisibility(View.GONE);
            }
            return view;
        }
        private class ViewHolder {
            public TextView tvSubTitle;
            public TextView tvLocation;
            public TextView tvSize;
            public TextView tvName;
            public ImageView ivIcon;
        }
    }
}

