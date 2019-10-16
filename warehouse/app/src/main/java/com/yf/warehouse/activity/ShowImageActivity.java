package com.yf.warehouse.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.yf.warehouse.R;
import com.yf.warehouse.utils.Constant;

/**
 * 掌握数据传递，以及如何用代码计算动态引用资源文件
 */
public class ShowImageActivity extends Activity {
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        imageView = findViewById(R.id.myimageview);
        Intent intent = getIntent();
        if(intent != null){
            int imageNum = intent.getIntExtra(Constant.IMAGE_INTENT_EXTRA_VALUE,0);
            int resId = getResources().getIdentifier("abum" + imageNum, "mipmap", getPackageName());
            imageView.setImageResource(resId);
        }
    }
}