package com.souhou.watersystem.ui.activity.FaultActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.souhou.watersystem.R;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.FaultSubPicAdapter;
import com.souhou.watersystem.utils.ImageDeal;
import com.souhou.watersystem.utils.LoadingDialog;
import com.souhou.watersystem.utils.SnackBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class FaultSubNewActivity extends BaseBackActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.pic_gridView)
    GridView picGridView;
    @BindView(R.id.ed_input_explain)
    EditText inPutText;

    public static final int IMAGE_PICKER = 1004;
    private FaultSubPicAdapter adapter;
    private List<ImageItem> mList = new ArrayList<>();
    MyApplication app;
    private String id, ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_sub_new);
        ButterKnife.bind(this);
        setTitle("故障处理详情");
        initView();
    }

    public void initView() {
        app = (MyApplication) getApplication();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        ID = intent.getStringExtra("ID");
        tvName.setText(app.getUsername());
        adapter = new FaultSubPicAdapter(mList, this, picGridView);
        picGridView.setAdapter(adapter);
        picGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == mList.size()) {
                    if (mList.size() == 4) {
                        SnackBar.make(picGridView, "最多添加四张");
                    } else {
                        Intent intent = new Intent(FaultSubNewActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, IMAGE_PICKER);
                    }
                } else {
                    dialog(i);
                }
            }
        });
    }

    private void request(JSONObject json) {
        LoadingDialog.createLoadingDialog(this, "正在提交...");
        OkHttpUtils
                .postString()
                .url(ServerConfig.BX_SAVE_URL)
                .content(json.toJSONString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LoadingDialog.closeDialog();
                        SnackBar.make(picGridView, "请求失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LoadingDialog.closeDialog();
                        JSONObject json = JSON.parseObject(response);
                        SnackBar.make(picGridView, json.getString("result").toString());
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                mList.addAll((ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));
                adapter.notifyDataSetChanged();
            } else {
                SnackBar.make(picGridView, "没有数据");
            }
        }
    }

    @OnClick(R.id.bt_sub)
    public void onViewClicked() {
        if (mList.size() > 0) {
            String content = inPutText.getText().toString();
            Map<String, String> map = new HashMap<>();
            map.put("processID", ID);
            map.put("processContent", content);
            String Pic = "";
            for (int i = 0; i < mList.size(); i++) {
                String path = mList.get(i).path;
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                String photo = ImageDeal.convertIconToString(bitmap);
                if (Pic.equals("")) {
                    Pic = photo;
                } else {
                    Pic = Pic + "," + photo;
                }
            }
            map.put("processPic", Pic);
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
            request(jsonObject);
        } else {
            SnackBar.make(picGridView, "请添加图片");
        }
    }

    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
