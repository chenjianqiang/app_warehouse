package com.yf.warehouse.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.yf.warehouse.R;
import com.yf.warehouse.adapter.WebPicAdapter;
import com.yf.warehouse.data.WebPicData;
import com.yf.warehouse.web.ApiCenter;
import com.yf.warehouse.web.SimpleCallback;
import com.yf.warehouse.web.WebService;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author cjq on 2019/10/15
 * 掌握通用RecyclerView对应adapter用法，RecyclerView列表用法，List对象数据与adapter数据绑定
 * RecyclerView列表点击，接口使用，界面跳转时数据传递 ，布局排版，播放控件使用等知识。
 */
public class WebPicShowActivity extends Activity {
    private Context context;
    private RecyclerView recyclerView;
    private TextView tvEmpty;
    private WebPicAdapter webPicAdapter;
    private WebService webService;
    private Call responseCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpic_show);
        recyclerView = findViewById(R.id.recyclerView);
        tvEmpty = findViewById(R.id.tvEmpty);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /*StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);//瀑布流布局*/
        loadWebPicData();
    }

    private void loadWebPicData() {
        webService = ApiCenter.getInstance(this).getService(WebService.class);
        responseCall = webService.getImageData("js",1,8);
        responseCall.enqueue(new SimpleCallback<WebPicData>(){
            @Override
            protected void onSuccess(Response<WebPicData> response) {
                WebPicData webPicData = response.body();
                webPicAdapter = new WebPicAdapter(WebPicShowActivity.this,null,webPicData);
                recyclerView.setAdapter(webPicAdapter);
                tvEmpty.setVisibility(View.GONE);
            }

            @Override
            protected void onServerLogicError(Response<WebPicData> response) {
                super.onServerLogicError(response);
            }

            @Override
            protected void onEnd() {
                super.onEnd();
            }
        });
    }
}
