package com.souhou.watersystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.BXRepairBean;
import com.souhou.watersystem.utils.DatetoStringFormat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/24.
 */

public class BXRecordAdapter extends BaseAdapter {
    private List<BXRepairBean.BaoXiuJiLuBean> list;
    private LayoutInflater inflater;
    Context context;

    public BXRecordAdapter(List<BXRepairBean.BaoXiuJiLuBean> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
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
            view = inflater.inflate(R.layout.adapter_bx_handel, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.tvAddress.setText(DatetoStringFormat.StringToStrLong(list.get(i).getRepairs_getTime() + ""));
        vh.tvTime.setText(list.get(i).getRepairs_User());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_address)
        TextView tvAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
