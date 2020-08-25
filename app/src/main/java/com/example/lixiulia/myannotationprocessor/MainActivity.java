package com.example.lixiulia.myannotationprocessor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.annotation.Route;

@Route(path="/hello/act1", group = "main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
