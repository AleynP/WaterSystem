package com.souhou.watersystem.ui.activity.NewsActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
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
import com.souhou.watersystem.ui.adapter.FaultSubPicAdapter;
import com.souhou.watersystem.utils.ImageDeal;
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

public class NewAddSubActivity extends BaseBackActivity {

    @BindView(R.id.water_id)
    TextView waterId;
    @BindView(R.id.ed_water_number)
    EditText edWaterNumber;
    @BindView(R.id.bt_sub)
    Button btSub;
    @BindView(R.id.gridView1)
    GridView gridView1;

    public static final int IMAGE_PICKER = 1004;
    @BindView(R.id.ddk_new_sub)
    ProgressBar ddkNewSub;
    private FaultSubPicAdapter adapter;
    private List<ImageItem> mList = new ArrayList<>();
    String waterid;
    String number = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add_sub);
        ButterKnife.bind(this);
        setTitle("提交新装水表");
        InItView();
    }

    private void InItView() {
        ddkNewSub.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        waterid = intent.getStringExtra("water_id");
        waterId.setText(waterid);
        adapter = new FaultSubPicAdapter(mList, this, gridView1);
        gridView1.setAdapter(adapter);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (position == mList.size()) {
                    if (mList.size() == 4) {
                        SnackBar.make(gridView1, "最多添加四张");
                    } else {
                        Intent intent = new Intent(NewAddSubActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, IMAGE_PICKER);
                    }
                } else {
                    dialog(position);
                }
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
                SnackBar.make(gridView1, "没有数据");
            }
        }

    }


    private void Okhttp(JSONObject jsonObject) {
        ddkNewSub.setVisibility(View.VISIBLE);
//        Log.i("TAG", resMap.toString());
//        Log.i("TAG", jsonObject.toJSONString());
        if (!number.equals("")) {
            OkHttpUtils
                    .postString()
                    .url(ServerConfig.AZ_BC_URL)
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .content(jsonObject.toJSONString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            ddkNewSub.setVisibility(View.INVISIBLE);
                            SnackBar.make(ddkNewSub, "请求失败" + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            ddkNewSub.setVisibility(View.INVISIBLE);
                            SnackBar.make(ddkNewSub, "提交成功");
                        }

                        @Override
                        public void inProgress(float progress, long total, int id) {
                            super.inProgress(progress, total, id);

                        }
                    });
        } else {
            SnackBar.make(btSub, "请完善水表信息");
        }

    }

    @OnClick(R.id.bt_sub)
    public void onViewClicked() {
        if (mList.size() > 0) {
            number = edWaterNumber.getText().toString();
            Map<String, String> resMap = new HashMap<>();
            resMap.put("WaterMeterID", waterid);
            resMap.put("WaterMeterNumber", number);
            String Pic = "";
            for (int i = 0; i < mList.size(); i++) {
                String path = mList.get(i).path;
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                String photo = ImageDeal.convertIconToString(bitmap);
                if (Pic.equals("")) {
                    Pic = photo;
                } else {
                    Pic = photo + "," + Pic;
                }

            }
            resMap.put("WaterMeterPic", Pic);
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resMap);
            Okhttp(jsonObject);

        } else {
            SnackBar.make(btSub, "请添加图片");
        }
    }


    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewAddSubActivity.this);
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
