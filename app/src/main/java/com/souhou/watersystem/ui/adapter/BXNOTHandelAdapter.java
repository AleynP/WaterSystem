package com.souhou.watersystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.BXNotHandelBean;
import com.souhou.watersystem.utils.DatetoStringFormat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/24.
 */

public class BXNOTHandelAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<BXNotHandelBean.WeiChuLiBaoXiuBean> mList;
    private Context context;

    public BXNOTHandelAdapter(List<BXNotHandelBean.WeiChuLiBaoXiuBean> mList, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
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
        vh.tvAddress.setText(DatetoStringFormat.StringToStrLong(mList.get(i).getRepairs_SendTime() + ""));
        vh.tvTime.setText(mList.get(i).getRepairs_User());
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
