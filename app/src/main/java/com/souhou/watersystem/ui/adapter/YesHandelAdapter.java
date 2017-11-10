package com.souhou.watersystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.YesHandelBean;
import com.souhou.watersystem.utils.DatetoStringFormat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/21.
 */

public class YesHandelAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<YesHandelBean.YiChuLiBaoZhuangBean> mList;
    Context context;

    public YesHandelAdapter(Context context, List<YesHandelBean.YiChuLiBaoZhuangBean> mList) {
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
            view = inflater.inflate(R.layout.adapter_new_add, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.tvName.setText(mList.get(i).getInstallation_User());
        vh.tvPhone.setText(mList.get(i).getInstallation_Userphone() + "");
        vh.tvSendtime.setText(DatetoStringFormat.StringToStrLong(mList.get(i).getInstallation_SendTime() + ""));
        vh.tvGettime.setText(DatetoStringFormat.StringToStrLong(mList.get(i).getInstallation_GetTime() + ""));
        vh.tvAddress.setText(mList.get(i).getInstallation_Address());
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
