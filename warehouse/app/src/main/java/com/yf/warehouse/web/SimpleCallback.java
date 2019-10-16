package com.yf.warehouse.web;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author cjq
 */
public class SimpleCallback<T> implements Callback<T>{
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()){
            if(response.code() == 200){
                onSuccess(response);
            }else{
                onServerLogicError(response);
            }
        }else {
            onNetworkError(response, null);
        }
        onEnd();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (!call.isCanceled()) {
            onNetworkError(null, t);
        }
        onEnd();
    }
    protected void onSuccess(Response<T> response) {
    }
    protected void onServerLogicError(Response<T> response) {
    }
    protected void onNetworkError(Response<T> response, Throwable t) {
    }
    protected void onEnd() {
    }
}
