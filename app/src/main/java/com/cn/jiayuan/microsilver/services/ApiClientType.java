package com.cn.jiayuan.microsilver.services;


import android.support.annotation.NonNull;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by Tiger on 2017/10/23
 */

public interface ApiClientType {



   @NonNull Observable<Boolean> login();


}
