package com.estyle.recyclerviewmultilayoutdemo.adapter;


import android.content.Context;
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

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private List<ItemBean> datas;

    public HorizontalAdapter(Context context) {
        this.context = context;
        datas = new ArrayList<>();
    }

    public void addDatas(List<ItemBean> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_horizontal, parent, false);
        ViewHolder holder = new ViewHolder(itemView);
//        setOnItemListener(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemBean item = datas.get(position);
        holder.textView.setText(item.getTitle());
        holder.imageView.setImageResource(item.getIconId());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
            imageView = ((ImageView) itemView.findViewById(R.id.iv));
        }

    }

    private void setOnItemListener(View itemView) {
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int position;
        switch (view.getId()) {
            default:
                if (onItemClickListener != null) {
                    position = ((RecyclerView) view.getParent()).getChildLayoutPosition(view);
                    onItemClickListener.onItemClick((RecyclerView) view.getParent(), view, position, getItemId(position));
                }
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        int position;
        switch (view.getId()) {
            default:
                if (onItemLongClickListener != null) {
                    position = ((RecyclerView) view.getParent()).getChildLayoutPosition(view);
                    onItemLongClickListener.onItemLongClick((RecyclerView) view.getParent(), view, position, getItemId(position));
                }
                break;
        }
        return true;
    }

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(RecyclerView parent, View view, int position, long id);
    }

    public interface OnItemLongClickListener {
        public void onItemLongClick(RecyclerView parent, View view, int position, long id);
    }

}