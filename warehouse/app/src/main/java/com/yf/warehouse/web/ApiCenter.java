package com.yf.warehouse.web;

import android.content.Context;

import com.yf.warehouse.utils.Constant;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author cjq
 * 基于Retrofit请求构建
 */
public class ApiCenter {
    private static ApiCenter instance = null;
    private String baseUrl = Constant.API_HOST;
    private Retrofit.Builder builder;
    private HashMap<Class, Object> cacheServices = new HashMap<>();

    private ApiCenter(Context context) {
        builder = new Retrofit.Builder().client(new OkHttpClient.Builder().connectTimeout(5,
                TimeUnit.SECONDS).build()).addConverterFactory(GsonConverterFactory.create());
    }

    public static ApiCenter getInstance(Context context) {
        synchronized (ApiCenter.class) {
            if (instance == null) {
                instance = new ApiCenter(context);
            }
        }
        return instance;
    }

    public <T> T getService(Class<T> clazz) {
        Object cache = cacheServices.get(clazz);
        if (cache == null) {
            T service = builder.baseUrl(baseUrl).build().create(clazz);
            cacheServices.put(clazz, service);
            return service;
        } else {
            return (T) cache;
        }
    }
}
