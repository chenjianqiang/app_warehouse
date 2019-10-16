package com.yf.warehouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.yf.warehouse.R;
import com.yf.warehouse.adapter.FeatureAdapter;
import com.yf.warehouse.viewhelper.DemoDetails;

/**
 * @author cjq on 2019/10/14
 * 掌握通用adapter用法，ListView列表用法，数组与adapter数据绑定
 * ListView列表点击，界面跳转，布局排版等知识。
 */
public class MainActivity extends AppCompatActivity {
    //AppCompatActivity自带系统ActionBar
    private ListView listView;
    private final DemoDetails[] demos = {
            new DemoDetails(R.string.module1_name, R.string.module1_descripton, PhotoAbumActivity.class),
            new DemoDetails(R.string.module2_name, R.string.module2_descripton, CinemaActivity.class),
            new DemoDetails(R.string.module3_name,R.string.module3_descripton, WidgetStyleActivity.class),
            new DemoDetails(R.string.module4_name, R.string.module4_descripton, WebPicShowActivity.class),
            new DemoDetails(R.string.module5_name, R.string.module5_descripton, ListViewTitleGroupActivity.class),
            new DemoDetails(R.string.module6_name, R.string.module6_descripton, MapDemoActivity.class)
    };
    private FeatureAdapter featureAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.mylistview);
        featureAdapter = new FeatureAdapter(this,demos);
        listView.setAdapter(featureAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DemoDetails demoDetails = featureAdapter.getItem(position);
                if(demoDetails.activityClass != null){
                    Intent intent = new Intent(MainActivity.this,demoDetails.activityClass);
                    startActivity(intent);
                }
            }
        });

    }
}
