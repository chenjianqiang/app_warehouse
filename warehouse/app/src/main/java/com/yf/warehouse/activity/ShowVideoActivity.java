package com.yf.warehouse.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.yf.warehouse.R;
import com.yf.warehouse.utils.Constant;

/**
 * @author cjq on 2019/10/15
 * 掌握权限申请，在线视频播放
 */
public class ShowVideoActivity extends Activity {
    private VideoView video_view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_viewer);
        video_view = findViewById(R.id.video_view);
        if (ContextCompat.checkSelfPermission(ShowVideoActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ShowVideoActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideoPath();  //	初始化VideoView
        }
    }

    private void initVideoPath() {
        Intent intent = getIntent();
        if(intent != null){
            String netVideoUrl = intent.getStringExtra(Constant.IMAGE_INTENT_EXTRA_VALUE);
            Uri uri = Uri.parse(netVideoUrl);
            video_view.setVideoURI(uri);//播放网络视频或资源目录
            video_view.start();//	开始播放
            video_view.requestFocus();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPath();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
}