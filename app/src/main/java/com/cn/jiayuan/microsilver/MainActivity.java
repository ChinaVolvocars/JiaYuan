package com.cn.jiayuan.microsilver;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.jiayuan.microsilver.services.ApiFactory;
import com.cn.jiayuan.microsilver.services.ApiService;
import com.orhanobut.logger.Logger;

import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends RxAppCompatActivity {

    @BindView(R.id.textview)
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.textview)
    public void OnClickTextView(){
        ApiService apiService = ApiFactory.getApiServiceSingleton();

        Observable login = apiService.login("15136221475", "123456");
        login .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // FIXME: 2017/10/31
                        Logger.e("onSubscribe");

                    }

                    @Override
                    public void onNext(Object o) {
                        Logger.e("onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("onError");
                    }

                    @Override
                    public void onComplete() {
                        Logger.e("onComplete");
                    }
                });
    }

}
