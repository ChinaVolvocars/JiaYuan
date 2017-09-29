package com.cn.jiayuan.microsilver;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;


public class JYApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();


    }

    /**
     * init Logger
     */
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("JiaYuan tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        //.logStrategy(customLog) (Optional) Changes the log strategy to print out. Default LogCat

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}