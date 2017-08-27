package com.souhou.watersystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.CBMeterBookBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/27.
 */

public class CBMeterBookAdapter extends BaseAdapter {
    private List<CBMeterBookBean.MeterBookListBean> list;
    private LayoutInflater inflater;
    private Context mContext;

    public CBMeterBookAdapter(List<CBMeterBookBean.MeterBookListBean> list, Context mContext) {
        this.list = list;
        this.inflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = inflater.inflate(R.layout.adpter_cb_book, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.tvBookName.setText(list.get(i).getMeterBook_Name());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_book_name)
        TextView tvBookName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
