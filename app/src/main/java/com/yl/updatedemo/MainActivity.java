package com.yl.updatedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.lzh.framework.updatepluginlib.UpdateBuilder;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UpdateBuilder.create().check(this);
    }
}
