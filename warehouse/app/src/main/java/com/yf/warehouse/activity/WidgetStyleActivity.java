package com.yf.warehouse.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.yf.warehouse.R;
import com.yf.warehouse.viewhelper.ConfirmDialog2;

/**
 * @author cjq on 2019/10/15
 * 这里收集常用的控件样式，供以后使用
 */
public class WidgetStyleActivity extends Activity implements View.OnClickListener {
    private ConfirmDialog2 mDeleteDialog;
    private PopupWindow mMenuPopup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style);
        initView();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bt_popup:
                showMoreClickPopupMenu(view);
                break;
            case R.id.bt_dialog:
                showDelete();
                break;
        }

    }
    private void initView() {
        findViewById(R.id.bt_popup).setOnClickListener(this);
        findViewById(R.id.bt_dialog).setOnClickListener(this);
    }


    private void showMoreClickPopupMenu(View v){
        if (mMenuPopup == null) {
            mMenuPopup = new PopupWindow(this);
            mMenuPopup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mMenuPopup.setWidth(337);
            mMenuPopup.setHeight(363);
            mMenuPopup.setFocusable(true);
            FrameLayout parent = new FrameLayout(this);
            @SuppressLint("InflateParams")
            ViewGroup vgMenu = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.layout_popup_menu, parent, false);
            for (int i = 0; i < vgMenu.getChildCount(); i++) {
                vgMenu.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View menuItem) {
                        mMenuPopup.dismiss();
                        if (menuItem.getId() == R.id.btn_delete) {
                            showDelete();
                        }else if (menuItem.getId() == R.id.btn_edit) {
                            Toast.makeText(WidgetStyleActivity.this,"XX编辑界面",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            mMenuPopup.setContentView(vgMenu);
        }
        mMenuPopup.showAsDropDown(v, -170, -19);
    }

    private void showDelete(){
        if (mDeleteDialog == null) {
            mDeleteDialog = new ConfirmDialog2(this)
                    .setButton(DialogInterface.BUTTON_POSITIVE, "删除")
                    .setButton(DialogInterface.BUTTON_NEGATIVE, "取消")
                    .setTitle("删除XX")
                    .setMessage("确定删除该条xx吗")
                    .setOnClickListener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == DialogInterface.BUTTON_POSITIVE) {
                                Toast.makeText(WidgetStyleActivity.this,"XX已删除",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        mDeleteDialog.show();
    }
}

