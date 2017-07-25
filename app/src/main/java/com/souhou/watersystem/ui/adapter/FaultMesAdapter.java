package com.souhou.watersystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.RepairBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/23.
 */

public class FaultMesAdapter extends BaseAdapter {
    private List<RepairBean.RepairsBean> mList;
    private LayoutInflater inflater;
    private Context context;

    public FaultMesAdapter(List<RepairBean.RepairsBean> mList, Context context) {
        this.mList = mList;
        this.inflater = LayoutInflater.from(context);
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
            view = inflater.inflate(R.layout.adpate_newsr, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.tvUser.setText(mList.get(i).getRepairs_User());
        vh.tvTime.setText(mList.get(i).getRepairs_Time() + "");
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_address)
        TextView tvUser;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}