package com.souhou.watersystem.ui.activity.FaultActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.souhou.watersystem.utils.SnackBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

public class FaultSubNewActivity extends BaseBackActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.pic_gridView)
    GridView picGridView;
    @BindView(R.id.ed_input_explain)
    EditText inPutText;
    @BindView(R.id.bt_sub)
    Button btSub;

    public static final int IMAGE_PICKER = 1004;
    @BindView(R.id.ddk_sub)
    ProgressBar ddkSub;
    private FaultSubPicAdapter adapter;
    private List<ImageItem> mList = new ArrayList<>();
    MyApplication app;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_sub_new);
        ButterKnife.bind(this);
        setTitle("新增故障处理");
        initView();
    }

    public void initView() {
        ddkSub.setVisibility(View.GONE);
        app = (MyApplication) getApplication();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
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
        OkHttpUtils
                .postString()
                .url(ServerConfig.BX_SAVE_URL)
                .content(String.valueOf(json))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new Callback() {

                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        ddkSub.setVisibility(View.GONE);
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
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
            map.put("processID", id);
            map.put("processContent", content);
            for (int i = 0; i < mList.size(); i++) {
                String path = mList.get(i).path;
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                String photo = ImageDeal.convertIconToString(bitmap);
                map.put("processPic" + (i + 1), photo);
            }
            ddkSub.setVisibility(View.VISIBLE);
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
            request(jsonObject);
        } else {
            SnackBar.make(btSub, "请添加图片");
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
