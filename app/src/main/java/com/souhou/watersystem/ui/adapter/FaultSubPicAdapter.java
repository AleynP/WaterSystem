package com.souhou.watersystem.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.lzy.imagepicker.bean.ImageItem;
import com.souhou.watersystem.R;
import com.souhou.watersystem.utils.PicassoImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/4.
 */

public class FaultSubPicAdapter extends BaseAdapter {
    private List<ImageItem> mList;
    private LayoutInflater inflater;
    private GridView gridView;
    Activity activity;

    public FaultSubPicAdapter(List<ImageItem> mList, Activity activity, GridView gridView) {
        this.mList = mList;
        this.inflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.gridView = gridView;
    }

    @Override
    public int getCount() {
        return mList == null ? 1 : mList.size() + 1;//返回listiview数目加1;
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

        int size = gridView.getWidth();
        PicassoImageLoader pic = new PicassoImageLoader();
        if (mList != null && i < mList.size()) {
            pic.displayImage(activity, mList.get(i).path, vh.imageView1, size, size);
            return view;
        } else {
            Picasso.with(activity).load(R.mipmap.new_add_img).into(vh.imageView1);
            return view;
        }
    }

    static class ViewHolder {
        @BindView(R.id.imageView1)
        ImageView imageView1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
