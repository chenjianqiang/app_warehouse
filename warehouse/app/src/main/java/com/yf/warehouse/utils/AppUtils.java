package com.yf.warehouse.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.yf.warehouse.data.AppBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 获得手机中安装的所有的应用的信息
 */
public class AppUtils {
    /**
     * 获得手机中安装的所有的应用的信息
     * @param ctx
     * @return
     */
    public static List<AppBean> getAllAppInfo(Context ctx) {
        List<AppBean> allAppInfo = new ArrayList<AppBean>();
        // 包管理器，管理手机 中所有的APK 安装包
        PackageManager pm = ctx.getPackageManager();    //   pm  project manager 项目经理
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : installedPackages) {
            // PackageInfo 包含AndroidManifest清单文件中，所有的信息
            // ApplicationInfo 包含 AndroidManifest清单文件中 , application中的所有的信息
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;

            AppBean appBean = new AppBean();
            allAppInfo.add(appBean); // 添加至集合
            // 设置包名

            appBean.packageName = packageInfo.packageName;
            // 获得应用名称
            appBean.appName = applicationInfo.loadLabel(pm).toString();
            // 应用图标
            appBean.appIcon = applicationInfo.loadIcon(pm);
//			applicationInfo.dataDir; // /data/data/包名 路径
            String apkPath = applicationInfo.sourceDir; // 该应用apk 的路径
//			System.out.println(appBean.appName+ " : "+apkPath);
            // 为apkPath 赋值
            appBean.apkPath = apkPath;
            File apkFile = new File(apkPath);
            appBean.appSize = apkFile.length();
            // 根据路径判断是否是系统应用
            if (apkPath.startsWith("/data")) { // 用户应用
                appBean.isSys = false;
                System.out.println(appBean.appName + " 根据 路径 值判断，是用户应用");
            } else {
                // 系统应用
                appBean.isSys = true;
                System.out.println(appBean.appName + " 根据 路径 值判断，是系统应用");
            }
            // 根据flag 值来判断是否是系统应用
            // 如果不等于0，说明批配成功，那么当前应用，拥有该 FLAG 值标注的属性
            if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                System.out.println(appBean.appName + " 根据 flag 值判断，是系统应用");
            } else {
                System.out.println(appBean.appName + " 根据 flag 值判断，是用户应用");
            }
            if ((applicationInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) { //
                appBean.isInSd = true;
            } else {
                appBean.isInSd = false;
            }
        }
        //SystemClock.sleep(500); // 休眠2秒，模拟耗时的情况
        return allAppInfo;
    }
}


