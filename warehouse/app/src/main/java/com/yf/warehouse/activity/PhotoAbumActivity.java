package com.yf.warehouse.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.yf.warehouse.R;
import com.yf.warehouse.adapter.PhotoAbumAdapter;
import com.yf.warehouse.interfaces.ItemClickCallback;
import com.yf.warehouse.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjq on 2019/10/14
 * <家庭相册>
 * 掌握通用RecyclerView对应adapter用法，RecyclerView列表用法，List数据与adapter数据绑定
 * RecyclerView列表点击，接口使用，界面跳转时数据传递 ，布局排版，以及如何用代码计算动态引用资源文件等知识。
 */
public class PhotoAbumActivity extends Activity implements ItemClickCallback {
    private List<Integer> images;
    private RecyclerView recyclerView;
    private PhotoAbumAdapter photoAbumAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoabum);
        recyclerView = findViewById(R.id.recycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        initDatas();
        photoAbumAdapter = new PhotoAbumAdapter(this,this,images);
        recyclerView.setAdapter(photoAbumAdapter);
    }

    private void initDatas() {
        images = new ArrayList<>();
        images.add(R.mipmap.abum0);
        images.add(R.mipmap.abum1);
        images.add(R.mipmap.abum2);
        images.add(R.mipmap.abum3);
        images.add(R.mipmap.abum4);
        images.add(R.mipmap.abum5);
        images.add(R.mipmap.abum6);
    }

    @Override
    public void itemClick(int pos) {
        //实现ItemClickCallback接口的方法:itemClick
        Toast.makeText(PhotoAbumActivity.this,"正在点击"+pos+"号照片",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PhotoAbumActivity.this,ShowImageActivity.class);
        intent.putExtra(Constant.IMAGE_INTENT_EXTRA_VALUE,pos);
        startActivity(intent);
    }
}
