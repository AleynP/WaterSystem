package com.souhou.watersystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.souhou.watersystem.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/31.
 */

public class BXPicAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<String> mList;
    Context context;

    public BXPicAdapter(List<String> mList, Context context) {
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
            view = inflater.inflate(R.layout.adapter_simp, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        String url = mList.get(i);
        Picasso.with(context).load(url).into(vh.imageView1);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.imageView1)
        ImageView imageView1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
