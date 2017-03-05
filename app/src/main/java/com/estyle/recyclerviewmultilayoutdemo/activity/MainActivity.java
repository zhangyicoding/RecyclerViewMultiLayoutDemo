package com.estyle.recyclerviewmultilayoutdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.estyle.recyclerviewmultilayoutdemo.R;
import com.estyle.recyclerviewmultilayoutdemo.adapter.MainAdapter;
import com.estyle.recyclerviewmultilayoutdemo.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * RecyclerView多套布局
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        MainAdapter adapter = new MainAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.addDatas(getNormalDatas(), getHorizontalDatas());
    }

    private List<ItemBean> getNormalDatas() {
        List<ItemBean> datas = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            ItemBean item = new ItemBean(String.format(Locale.getDefault(), "第%d个数据", i), R.mipmap.ic_launcher);
            datas.add(item);
        }
        return datas;
    }


    private List<List<ItemBean>> getHorizontalDatas() {
        List<List<ItemBean>> horizontalDatas = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            List<ItemBean> rowDatas = new ArrayList<>();
            for (int j = 1; j <= 10; j++) {
                ItemBean item = new ItemBean(String.format(Locale.getDefault(), "%d行%d个", i, j), R.mipmap.ic_launcher);
                rowDatas.add(item);
            }
            horizontalDatas.add(rowDatas);
        }
        return horizontalDatas;
    }


}
