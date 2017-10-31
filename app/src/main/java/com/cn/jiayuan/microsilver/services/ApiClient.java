package com.cn.jiayuan.microsilver.services;

import com.cn.jiayuan.microsilver.BuildConfig;
import com.cn.jiayuan.microsilver.MainApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tiger on 2017/10/23
 */

public class ApiClient  {

   private static String API_URL = BuildConfig.api_host + "/rest/";

    private static OkHttpClient okHttpClient;
    private static Object object = new Object();
    final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    static {
        initOkHttpClient();
    }

    final ApiService apiService;

    private static void initOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        if (okHttpClient == null) {
            synchronized (ApiClient.class) {
                if (okHttpClient == null) {
                    Cache cache = new Cache(new File(MainApplication.instance().getCacheDir(), "HttpCache"), 1024 * 1024 * 10);
                    okHttpClient=new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(loggingInterceptor)
                            .addNetworkInterceptor(new CacheInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    public ApiClient() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addConverterFactory(createGsonConverterFactory(gson))
                .addCallAdapterFactory(createRxJavaCallAdapterFactory())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    protected ApiService getApiService(){
        return apiService;
    }

    private static GsonConverterFactory createGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    private static RxJava2CallAdapterFactory createRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    public static class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            int maxAge = 60 * 60;
            int maxStale = 60 * 60 * 24;

            Request request = chain.request();

            if (true) {
                //有网络时从网络获取
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            } else {
                //无网络从缓存中获取
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            Response response = chain.proceed(request);

            if (true) {
                response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
            }else {
                response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
            }
            return response;
        }
    }

}

