package com.souhou.watersystem.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.UserInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/7/16.
 */

public class UserAdapter extends BaseAdapter {

    private List<UserInfo> list;
    private LayoutInflater inflater;

    public UserAdapter() {
    }

    public UserAdapter(List<UserInfo> stuList, Context context) {
        this.list = stuList;
        this.inflater = LayoutInflater.from(context);
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
        if (view == null) {
            view = inflater.inflate(R.layout.user_adpater, null);
        }
        //在view视图中查找id为image_photo的控件
        TextView name = (TextView) view.findViewById(R.id.user_name);
        name.setText(list.get(i).getUser_Name());
        name.setText(list.get(i).getUser_Number());
        return view;

        /*ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.user_adpater, null);
            viewHolder.textView = (TextView) view.findViewById(R.id.list_user);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(list.get(i));
        return view;
    }

    class ViewHolder {
        public TextView textView;
    }*/
    }
}
