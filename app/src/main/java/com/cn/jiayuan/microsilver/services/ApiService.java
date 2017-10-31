package com.cn.jiayuan.microsilver.services;


import android.support.annotation.NonNull;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Tiger on 2017/10/23
 */

public interface ApiService {


    @POST("/login")
    Observable<Enter> login( @Query("cellPhone") String cellPhone,
                             @Query("password") String password);

}
