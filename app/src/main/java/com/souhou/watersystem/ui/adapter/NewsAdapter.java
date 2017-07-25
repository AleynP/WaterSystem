package com.souhou.watersystem.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.NewsList;

import java.util.List;

/**
 * Created by Administrator on 2017/7/16.
 */

public class NewsAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<NewsList.InstallationBean> mList;
    private Context context;

    public NewsAdapter(Context context, List<NewsList.InstallationBean> mList) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mList = mList;
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
            vh = new ViewHolder();
            view = mLayoutInflater.inflate(R.layout.adpate_newsr, null);
            vh.tv_time = view.findViewById(R.id.tv_time);
            vh.tv_address = view.findViewById(R.id.tv_address);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        NewsList.InstallationBean inst = mList.get(i);
        vh.tv_time.setText(inst.getInstallation_User());
        vh.tv_address.setText(inst.getInstallation_Address());
        return view;
    }

    class ViewHolder {
        private TextView tv_time, tv_address;
    }


}
