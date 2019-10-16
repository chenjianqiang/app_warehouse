package com.yf.warehouse.data;

import android.graphics.drawable.Drawable;

public class AppBean {
    /**
     * 应用的包名
     */
    public String packageName;
    public String appName;
    public Drawable appIcon;
    /**
     * 应用的大小，即APK安装包的大小
     */
    public long appSize;
    /**
     * 判断是否是安装在SD卡中
     */
    public boolean isInSd;
    /**
     * 判断是否是系统应用
     */
    public boolean isSys;
    /**
     * APK文件的路径
     */
    public String apkPath;
}

