package com.souhou.watersystem.ui.activity.FaultActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.adapter.FaultSubPicAdapter;
import com.souhou.watersystem.utils.SnackBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FaultSubNewActivity extends BaseBackActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.ed_input_explain)
    EditText inPutText;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.bt_sub)
    Button btSub;

    private static int REQUEST_THUMBNAIL = 1;// 请求缩略图信号标识
    @BindView(R.id.img_text)
    ImageView imgText;
    private FaultSubPicAdapter adapter;
    private List<Uri> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_sub_new);
        ButterKnife.bind(this);
        setTitle("新增故障处理");
        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("name"));
        adapter = new FaultSubPicAdapter(mList, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == mList.size()) {
                    if (mList.size() == 4) {
                        SnackBar.make(gridView, "最多添加四张");
                    } else {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);       // 启动相机
                        startActivityForResult(intent, REQUEST_THUMBNAIL);
                    }
                } else {
                    dialog(i);
                }
            }
        });

    }

    private void request() {
        OkHttpUtils
                .get()
                .url(ServerConfig.BX_SAVE_URL)
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_THUMBNAIL) {
                /**
                 * * 通过这种方法取出的拍摄会默认压缩，因为如果相机的像素比较高拍摄出来的图会比较高清，
                 *如果图太大会造成内存溢出（OOM），因此此种方法会默认给图片尽心压缩
                 */
                Uri uri = data.getData();
                Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
                if (cursor.moveToFirst()) {
                    String videoPath = cursor.getString(cursor.getColumnIndex("_data"));// 获取绝对路径

                }
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
//                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
//                mList.add(uri);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @OnClick(R.id.bt_sub)
    public void onViewClicked() {
        request();
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
