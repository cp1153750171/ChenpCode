package com.example.administrator.searchviewdemo;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 */
public class StudentAdapter extends BaseAdapter implements Filterable{
    private Context context;
    private List<StudentInfo> list;
    private List<StudentInfo> listdata;//备份原始数据
    private MyFilter mFilter ;

    public StudentAdapter(Context context, List<StudentInfo> list) {
        this.context = context;
        this.list = list;
        this.listdata = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyView myView=null;
        if(convertView==null){
            convertView = View.inflate(context,R.layout.listview_item,null);
            myView = new MyView(convertView);
            convertView.setTag(myView);
        }else{
            myView = (MyView) convertView.getTag();
        }
        myView.age.setText(list.get(position).getAge()+"岁");
        myView.name.setText(list.get(position).getName());
        if(list.get(position).isSex()) {
            myView.sex.setText("男");
        }else{
            myView.sex.setText("女");
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (mFilter ==null){
            mFilter = new MyFilter();
        }
        return mFilter;
    }

    class MyView{
        public TextView age,name,sex;

        public MyView(View view) {
            age = (TextView) view.findViewById(R.id.item_age);
            name = (TextView) view.findViewById(R.id.item_name);
            sex = (TextView) view.findViewById(R.id.item_sex);
        }
    }
    //我们需要定义一个过滤器的类来定义过滤规则
    class MyFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults result = new FilterResults();
            List<StudentInfo> lists ;
            if (TextUtils.isEmpty(constraint)){//当过滤的关键字为空的时候，我们则显示所有的数据
                lists  = listdata;
            }else {//否则把符合条件的数据对象添加到集合中
                lists = new ArrayList<>();
                for (StudentInfo recomend:list){
                    if (recomend.getName().contains(constraint)){
                        lists.add(recomend);
                    }

                }
            }
            result.values = lists; //将得到的集合保存到FilterResults的value变量中
            result.count = lists.size();//将集合的大小保存到FilterResults的count变量中

            return result;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list = (List<StudentInfo>)results.values;
            if (results.count>0){
                notifyDataSetChanged();//通知数据发生了改变
            }else {
                notifyDataSetInvalidated();//通知数据失效
            }

        }
    }
}
