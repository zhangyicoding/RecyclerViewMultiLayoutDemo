package com.estyle.recyclerviewmultilayoutdemo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.estyle.recyclerviewmultilayoutdemo.R;
import com.estyle.recyclerviewmultilayoutdemo.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * p i
 * 0 0
 * 1 1
 * 2 -0
 * 3 2
 * 4 3
 * 5 -1
 * 6 4
 * 7 5
 * 8 -2
 * 9 6
 * 10 7
 * 11 -3 11 / 3
 * 12 8 12 / 3 * 2
 */
public class MainAdapter extends RecyclerView.Adapter {

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_HORIZONTAL = 1;

    private Context context;
    private List<ItemBean> datas;
    private List<List<ItemBean>> horizontalDatas;

    public MainAdapter(Context context) {
        this.context = context;
        datas = new ArrayList<>();
        horizontalDatas = new ArrayList<>();
    }

    public void addDatas(List<ItemBean> datas, List<List<ItemBean>> horizontalDatas) {
        this.datas.addAll(datas);
        this.horizontalDatas.addAll(horizontalDatas);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_NORMAL:
                View itemView = LayoutInflater.from(context).inflate(R.layout.item_normal, parent, false);
                holder = new NormalViewHolder(itemView);
                break;
            case TYPE_HORIZONTAL:
                RecyclerView horizontalRecyclerView = new RecyclerView(context);
                horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                HorizontalAdapter adapter = new HorizontalAdapter(context);
                horizontalRecyclerView.setAdapter(adapter);
                holder = new HorizontalViewHolder(horizontalRecyclerView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_NORMAL:
                int realPosition = 0;
                if (position % 3 == 0) {
                    realPosition = position / 3 * 2;
                } else if (position % 3 == 1) {
                    realPosition = position / 3 * 2 + 1;
                }
                ItemBean item = datas.get(realPosition);
                NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
                normalViewHolder.textView.setText(item.getTitle());
                normalViewHolder.imageView.setImageResource(item.getIconId());
                break;
            case TYPE_HORIZONTAL:
                HorizontalAdapter adapter = (HorizontalAdapter) ((HorizontalViewHolder) holder).recyclerView.getAdapter();
                adapter.addDatas(horizontalDatas.get(position / 3));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + horizontalDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 2) {
            return TYPE_HORIZONTAL;
        }
        return TYPE_NORMAL;
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView;
        }
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
            imageView = ((ImageView) itemView.findViewById(R.id.iv));
        }

    }


}