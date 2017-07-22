package com.souhou.watersystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.NotHandelBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/21.
 */

public class NotHandelAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<NotHandelBean.WeichuLiBaoZhuangBean> mlist;
    private Context context;

    public NotHandelAdapter(Context context, List<NotHandelBean.WeichuLiBaoZhuangBean> mlist) {
        this.inflater = LayoutInflater.from(context);
        this.mlist = mlist;
        this.context = context;
    }


    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = inflater.inflate(R.layout.handel_adapter,null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        NotHandelBean.WeichuLiBaoZhuangBean weichuLi = mlist.get(i);
        vh.tvName.setText(weichuLi.getInstallation_User());
        vh.tvPhone.setText(weichuLi.getInstallation_Userphone() + "");
        vh.tvGettime.setText(weichuLi.getInstallation_GetTime() + "");
        vh.tvSendtime.setText(weichuLi.getInstallation_SendTime() + "");
        vh.tvAddress.setText(weichuLi.getInstallation_Address());
        return view;

    }

    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_gettime)
        TextView tvGettime;
        @BindView(R.id.tv_sendtime)
        TextView tvSendtime;
        @BindView(R.id.tv_address)
        TextView tvAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
