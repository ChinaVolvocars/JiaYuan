package com.cn.jiayuan.microsilver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textview)
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }


    @OnClick(R.id.textview)
    public void onViewClicked() {
        Toast.makeText(this, "55555", Toast.LENGTH_SHORT).show();
    }

    public String setText() {
        return "llll";
    }

    public void logD(View view) {
        Log.e("debug", "clicked");
    }
}
