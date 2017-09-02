package com.example.administrator.searchviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private ListView listView;
    private List<StudentInfo> list;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        initEvents();
    }

    private void initEvents() {
        listView.setTextFilterEnabled(true);
        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {

                /*if (TextUtils.isEmpty(query)) {
                    listView.clearTextFilter();//搜索文本为空时，清除ListView的过滤
                }
                else {
                    listView.setFilterText(query);//设置过滤关键字

                }*/
                ListAdapter adapter = listView.getAdapter();
                if (adapter instanceof Filterable) {
                    Filter filter = ((Filterable) adapter).getFilter();
                    if (query == null || query.length() == 0) {
                        filter.filter(null);
                    } else {
                        filter.filter(query);
                    }
                }
                return false;
            }
            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                ListAdapter adapter = listView.getAdapter();
                if (adapter instanceof Filterable) {
                    Filter filter = ((Filterable) adapter).getFilter();
                    if (newText == null || newText.length() == 0) {
                        filter.filter(null);
                    } else {
                        filter.filter(newText);
                    }
                }
               /* if (!TextUtils.isEmpty(newText)){
                    listView.setFilterText(newText);
                }else{
                    listView.clearTextFilter();

                }*/
                return false;
            }
        });
        adapter = new StudentAdapter(this,list);
        listView.setAdapter(adapter);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new StudentInfo("张三",true,18));
        list.add(new StudentInfo("李四",true,28));
        list.add(new StudentInfo("王五",true,20));
        list.add(new StudentInfo("六麻子",true,21));
        list.add(new StudentInfo("秋水",true,25));
        list.add(new StudentInfo("小红",false,21));
        list.add(new StudentInfo("小白",true,25));
        list.add(new StudentInfo("辛夷",true,26));
        list.add(new StudentInfo("雏田",false,22));
        list.add(new StudentInfo("鸣人",true,26));
    }

    private void initViews() {
        listView = (ListView) findViewById(R.id.listview);
        searchView = (SearchView) findViewById(R.id.swarchview);
    }
}
