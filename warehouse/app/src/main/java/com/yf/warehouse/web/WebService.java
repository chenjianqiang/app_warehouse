package com.yf.warehouse.web;

import com.yf.warehouse.data.WebPicData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * https://blog.saintic.com/blog/240.html
 * 经过访问BING的网址最终发现，bing中文网一直在提供每日更新背景图片壁纸的json数据。
 * 访问网址：http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1
 * 根据上面地址的结构，我暂时研究到就三项属性有效，他们分别是
 * 1、format，非必要。我理解为输出格式，不存在或者不等于js，即为xml格式，等于js时，输出json格式；
 * 2、idx，非必要。不存在或者等于0时，输出当天的图片，-1为已经预备用于明天显示的信息，1则为昨天的图片，idx最多获取到之前16天的图片信息；*
 * 3、n，必要。这是输出信息的数量，比如n=1，即为1条，以此类推，至多输出8条；*
 * *号注释：此处我们要注意的时，是否正常的输出信息，与n和idx有关，通过idx的值，我们就可以获得之前bing所使用的背景图片的信息了。
 *
 * 需要在app目录的build.gradle中引入implementation 'com.squareup.retrofit2:retrofit:2.4.0'
 */
public interface WebService {
    @GET("HPImageArchive.aspx")
    Call<WebPicData> getImageData(
            @Query("format") String js,
            @Query("idx") int idx,
            @Query("n") int n);
}
