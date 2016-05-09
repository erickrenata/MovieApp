package com.movie.movieapp.network;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.movie.movieapp.utils.Constant;
import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Erick Renata on 07/05/2016.
 */
public class NetworkModule {

    public static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .serializeNulls()
                        .create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static OkHttpClient provideOkHttpClient() {
        OkHttpClient okClient = new OkHttpClient();
        okClient.setConnectTimeout(40, TimeUnit.SECONDS);
        okClient.setReadTimeout(40, TimeUnit.SECONDS);

        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        okClient.setCookieHandler(cookieManager);

        HttpLoggingInterceptor interceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.d("Movie App", message);
                    }
                });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okClient.interceptors().add(interceptor);
        okClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                Request newRequest = request.newBuilder()
                        .addHeader("Cache-Control", "no-cache")
                        .addHeader("Cache-Control", "no-store")
                        .build();

                return chain.proceed(newRequest);
            }
        });

        return okClient;
    }
}