package com.yf.warehouse.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author cjq
 */
public class MapDemoActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        //启动com.example.baidutest报名的样例应用的Activity
        intent.setAction("com.example.baidutest.action.XXXX_ACTION");
        intent.addCategory("com.example.baidutest.category.XXXX_CATEGORY");
        startActivity(intent);
        finish();
    }
}
