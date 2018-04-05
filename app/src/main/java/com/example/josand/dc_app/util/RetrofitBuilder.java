package com.example.josand.dc_app.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by josan on 13/02/2018.
 */

public class RetrofitBuilder {

    private Context mContext;
    private Retrofit retrofit;
    private String url;

    public RetrofitBuilder(Context mContext, String url) {
        this.mContext = mContext;
        this.url = url;
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private boolean isOnline(){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public Retrofit getRetrofit() {
        if(isOnline()){
            return retrofit;
        }else{
            Toast.makeText(mContext,"Hola", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
