System.out.println()方法讲解

权限没申请
http改成https

报res\values-v28\values-v28.xml:9:5-1
把compileSdkVersion、targetSdkVersion 降级后，会报上面错误

报找不到android.support.v7.widget.RecyclerView
这是因为gradle.properties里面 android.useAndroidX=true，android.enableJetifier=true 会把里面的support里面的控件转换成androidx，这样即使引用了support库，但是依然找不到。
解决：修改gradle.properties里面注释掉android.useAndroidX=true，android.enableJetifier=true

gradle同步失败问题
修改根目录下build.gradle文件 classpath 'com.android.tools.build:gradle:3.4.2'
修改gradle-wrapper.properties文件 distributionUrl=https\://services.gradle.org/distributions/gradle-5.1.1-all.zip

无法播放此视频
1.权限没申请
2.http改成https

<android.support.v7.widget.RecyclerView 不显示数据，没执行onCreateViewHolder，onBindViewHolder方法；
原因是没设置layoutManager，如下
app:layoutManager="android.support.v7.widget.LinearLayoutManager"