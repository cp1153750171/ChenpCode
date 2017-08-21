package com.example.administrator.liststretchdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.liststretchdemo.R;
import com.example.administrator.liststretchdemo.adapter.MyAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<String> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("我是第" + i + "个条目");
        }
        adapter = new MyAdapter(this,list);
        listview.setAdapter(adapter);
    }
}
