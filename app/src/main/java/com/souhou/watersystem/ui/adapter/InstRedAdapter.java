package com.souhou.watersystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.InsRedBean;
import com.souhou.watersystem.utils.DatetoStringFormat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/21.
 */

public class InstRedAdapter extends BaseAdapter {
    private List<InsRedBean.ZhuangBiaoJiLuBean> mList;
    private LayoutInflater inflater;
    private Context context;

    public InstRedAdapter(List<InsRedBean.ZhuangBiaoJiLuBean> mList, Context context) {
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
            view = inflater.inflate(R.layout.adapter_new_add, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.tv_id.setText(mList.get(i).getId() + "");
        vh.tvName.setText(mList.get(i).getInstallation_User());
        vh.tvPhone.setText(mList.get(i).getInstallation_Userphone() + "");
        String sendtim = DatetoStringFormat.StringToStrLong(mList.get(i).getInstallation_SendTime() + "");
        String gettim = DatetoStringFormat.StringToStrLong(mList.get(i).getInstallation_GetTime() + "");

        vh.tvSendtime.setText(sendtim);
        vh.tvGettime.setText(gettim);
        vh.tvAddress.setText(mList.get(i).getInstallation_Address());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_waterid)
        TextView tv_id;
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
