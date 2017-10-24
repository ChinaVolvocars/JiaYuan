package com.cn.jiayuan.microsilver;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class MainActivity extends RxAppCompatActivity {

    @BindView(R.id.textview)
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutId();


    @OnClick(R.id.textview)
    public void onViewClicked() {
        Toast.makeText(this, "点击事件", Toast.LENGTH_SHORT).show();
    }


}
