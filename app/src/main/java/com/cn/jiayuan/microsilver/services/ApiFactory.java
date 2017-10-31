package com.cn.jiayuan.microsilver.services;

/**
 * Created by Tiger on 2017/10/31
 */

public class ApiFactory {

    private static final Object monitor = new Object();
    static ApiService apiService = null;

    public static ApiService getApiServiceSingleton() {
        synchronized (monitor) {
            if (apiService == null) {
                apiService = new ApiClient().getApiService();
            }
            return apiService;
        }
    }

}
