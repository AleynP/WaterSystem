package com.souhou.watersystem.ui.activity.NewsActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.souhou.watersystem.R;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.adapter.FaultSubPicAdapter;
import com.souhou.watersystem.utils.ImageDeal;
import com.souhou.watersystem.utils.LogUtils;
import com.souhou.watersystem.utils.SnackBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class NewAddSubActivity extends BaseBackActivity {

    @BindView(R.id.water_id)
    TextView waterId;
    @BindView(R.id.ed_water_number)
    EditText edWaterNumber;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.bt_sub)
    Button btSub;

    private FaultSubPicAdapter adapter;
    private List<Uri> mList = new ArrayList<>();
    private Uri imageUri;                       //拍照Uri
    private String pathTakePhoto;              //拍照路径
    private final int TAKE_PHOTO = 3;       //拍照标记
    private final int IMAGE_OPEN = 1;        //打开图片标记
    private String pathImage;                //选择图片路径
    private Bitmap bmp;                      //导入临时图片
    private ArrayList<HashMap<String, Object>> imageItem;
    private SimpleAdapter simpleAdapter;     //适配器

    private List<String> list = new ArrayList<>();
    String uploadFile = "";
    String waterid;
    String number = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add_sub);
        ButterKnife.bind(this);
        setTitle("提交新装水表");
        Intent intent = getIntent();
        waterid = intent.getStringExtra("water_id");
        waterId.setText(waterid);
        InItView();
    }

    private void InItView() {
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.new_add_img);
        imageItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("itemImage", bmp);
        imageItem.add(map);
        simpleAdapter = new SimpleAdapter(this,
                imageItem, R.layout.adapter_simp,
                new String[]{"itemImage"}, new int[]{R.id.imageView1});
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object o, String s) {
                // TODO Auto-generated method stub
                if (view instanceof ImageView && o instanceof Bitmap) {
                    ImageView i = (ImageView) view;
                    i.setImageBitmap((Bitmap) o);
                    return true;
                }
                return false;
            }
        });
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (imageItem.size() == 10) { //第一张为默认图片
                    Toast.makeText(NewAddSubActivity.this, "图片数9张已满", Toast.LENGTH_SHORT).show();
                } else if (position == 0) { //点击图片位置为+ 0对应0张图片
//                    Toast.makeText(MainActivity.this, "添加图片", Toast.LENGTH_SHORT).show();
                    //选择图片
                    AddImageDialog();
                } else {
                    dialog(position);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //打开图片
//        if (resultCode == RESULT_OK && requestCode == IMAGE_OPEN) {
//            Uri uri = data.getData();
//            if (!TextUtils.isEmpty(uri.getAuthority())) {
//                //查询选择图片
//                Cursor cursor = getContentResolver().query(
//                        uri,
//                        new String[]{MediaStore.Images.Media.DATA},
//                        null,
//                        null,
//                        null);
//                //返回 没找到选择图片
//                if (null == cursor) {
//                    return;
//                }
//                //光标移动至开头 获取图片路径
//                cursor.moveToFirst();
//                pathImage = cursor.getString(cursor
//                        .getColumnIndex(MediaStore.Images.Media.DATA));
//                list.add(pathImage);
//            }
//        }  //end if 打开图片
        //拍照
        if (resultCode == RESULT_OK && requestCode == TAKE_PHOTO) {
            Intent intent = new Intent("com.android.camera.action.CROP"); //剪裁
            intent.setDataAndType(imageUri, "image/*");
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            //广播刷新相册
            Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intentBc.setData(imageUri);
            this.sendBroadcast(intentBc);
            mList.add(imageUri);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(pathImage)) {
            Bitmap addbmp = BitmapFactory.decodeFile(pathImage);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", addbmp);
            imageItem.add(map);
            simpleAdapter = new SimpleAdapter(this,
                    imageItem, R.layout.adapter_simp,
                    new String[]{"itemImage"}, new int[]{R.id.imageView1});
            simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object data,
                                            String textRepresentation) {
                    // TODO Auto-generated method stub
                    if (view instanceof ImageView && data instanceof Bitmap) {
                        ImageView i = (ImageView) view;
                        i.setImageBitmap((Bitmap) data);
                        return true;
                    }
                    return false;
                }
            });
            gridView.setAdapter(adapter);
            simpleAdapter.notifyDataSetChanged();
            //刷新后释放防止手机休眠后自动添加
            pathImage = null;
        }
    }

    private void Okhttp(String photo) {
        number = edWaterNumber.getText().toString();
        if (!number.equals("")) {
            HashMap map = new HashMap();
            map.put("WaterMeterID", waterid);
//            map.put("WaterMeterPic", photo);
            map.put("WaterMeterNumber", number);
            OkHttpUtils
                    .get()
                    .url(ServerConfig.AZ_BC_URL)
//                    .params(map)
                    .addParams("WaterMeterID", waterid)
//                    .addParams("WaterMeterPic", photo)
                    .addParams("WaterMeterNumber", number)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            SnackBar.make(btSub, e.getMessage().toString());
//                            Snackbar.make(btSub, "提交失败", Snackbar.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.i("TAG", "提交成功");
                            SnackBar.make(btSub, "提交成功");
                        }
                    });
        } else {
            SnackBar.make(btSub, "请完善水表信息");
        }

    }

    @OnClick(R.id.bt_sub)
    public void onViewClicked() {
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                uploadFile = list.get(i);
//            File file = new File(uploadFile);
                Bitmap bitmap = BitmapFactory.decodeFile(uploadFile);
                String photo = ImageDeal.convertIconToString(bitmap);
                Okhttp(photo);
//            Log.i("TAG", uploadFile);
            }
        } else {
            SnackBar.make(btSub, "请添加图片");
        }
    }

    protected void AddImageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewAddSubActivity.this);
        builder.setTitle("添加图片");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false); //不响应back按钮
        builder.setItems(new String[]{"相册选择", "拍照添加", "取消选择图片"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0: //本地相册
                                dialog.dismiss();
                                //选择图片
                                Intent intent = new Intent(Intent.ACTION_PICK,
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, IMAGE_OPEN);
                                //通过onResume()刷新数据
                                break;
                            case 1: //手机相机
                                dialog.dismiss();
                                File outputImage = new File(Environment.getExternalStorageDirectory(), "souhou_image.jpg");
                                pathTakePhoto = outputImage.toString();
                                try {
                                    if (outputImage.exists()) {
                                        outputImage.delete();
                                    }
                                    outputImage.createNewFile();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                imageUri = Uri.fromFile(outputImage);
                                Intent intentPhoto = new Intent("android.media.action.IMAGE_CAPTURE"); //拍照
                                intentPhoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                startActivityForResult(intentPhoto, TAKE_PHOTO);
                                break;
                            case 2: //取消添加
                                dialog.dismiss();
                                break;
                            default:
                                break;
                        }
                    }
                });
        //显示对话框
        builder.create().show();
    }

    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewAddSubActivity.this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                imageItem.remove(position);
                simpleAdapter.notifyDataSetChanged();
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
