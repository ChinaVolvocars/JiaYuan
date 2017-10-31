package com.cn.jiayuan.microsilver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RecylViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyl_view);
        TextView textView = findViewById(R.id.text);
        textView.setOnClickListener(this::oncli);


    }

    private void oncli(View view) {
        System.out.println("-----------------");
    }


}
