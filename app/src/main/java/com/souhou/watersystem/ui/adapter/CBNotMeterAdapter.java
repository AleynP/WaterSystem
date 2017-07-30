package com.souhou.watersystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.CBMeterBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.souhou.watersystem.bean.CBMeterBean.*;

/**
 * Created by Administrator on 2017/7/25.
 */

public class CBNotMeterAdapter extends BaseAdapter {
    private List<CBMeterBean.BenYueWeiChaoBean> mList;
    private LayoutInflater inflater;
    private Context context;

    public CBNotMeterAdapter(List<CBMeterBean.BenYueWeiChaoBean> list, Context context) {
        this.mList = list;
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
            view = inflater.inflate(R.layout.adapter_cb_meter, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.tvNumber.setText(mList.get(i).getUser_Number() + "");
        vh.tvName.setText(mList.get(i).getUser_Name() + "");
        vh.tvTime.setText(mList.get(i).getMeterReading_Time() + "");
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
