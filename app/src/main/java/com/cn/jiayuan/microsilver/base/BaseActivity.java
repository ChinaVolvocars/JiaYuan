package com.cn.jiayuan.microsilver.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cn.jiayuan.microsilver.RxAppCompatActivity;

/**
 * Created by Tiger on 2017/11/1
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());


    }

    protected abstract int getLayoutId() ;
}
