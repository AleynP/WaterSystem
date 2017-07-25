package com.souhou.watersystem.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.utils.ImageDeal;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class NewAddSubActivity extends AppCompatActivity {

    @BindView(R.id.water_id)
    TextView waterId;
    @BindView(R.id.ed_water_number)
    EditText edWaterNumber;
    @BindView(R.id.sub_img1)
    ImageView subImg1;
    @BindView(R.id.bt_sub)
    Button btSub;

    public static final int TAKE_PHOTO = 1;//拍照
    public static final int CROP_PHOTO = 2;//裁剪
    public static final int SELECT_PIC = 0;//从相册选择

    private Uri imageUri; //图片路径
    private String filename; //图片名称
    ImageDeal headImage;
    private Bitmap bitmap;
    private String waterid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add_sub);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        waterid = intent.getStringExtra("water_id");
        waterId.setText(waterid);
    }

    @OnClick({R.id.sub_img1, R.id.bt_sub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sub_img1:
                takePhoto();
                break;
            case R.id.bt_sub:
                Okhttp();
                break;
        }
    }

    private void Okhttp() {
        String photo = null;
        if (bitmap != null) {
            photo = ImageDeal.convertIconToString(bitmap);
            Snackbar.make(btSub, photo, Snackbar.LENGTH_SHORT).show();
        }
//        File file = null;
//        try {
//            file = new File(new URI(imageUri.toString()));
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
        String number = edWaterNumber.getText().toString();
        HashMap map = new HashMap();
        map.put("WaterMeterID", waterid);
        map.put("WaterMeterPic", photo);
        map.put("WaterMeterNumber", number);
        OkHttpUtils
                .get()
                .url(ServerConfig.AZ_BC_URL)
//                .addFile("WaterMeterPic", filename + ".jpg", file)
                .params(map)
//                .file(file)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Snackbar.make(btSub, e.getMessage().toString(), Snackbar.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Snackbar.make(btSub, "提交成功", Snackbar.LENGTH_SHORT).show();
                    }
                });

    }

    private void takePhoto() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        filename = format.format(date);
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File outputImage = new File(path, filename + ".jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将File对象转换为Uri并启动照相程序
        imageUri = Uri.fromFile(outputImage);
        Intent tTntent = new Intent("android.media.action.IMAGE_CAPTURE"); //照相
        tTntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
        startActivityForResult(tTntent, TAKE_PHOTO); //启动照相
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
//                Intent intent = new Intent("com.android.camera.action.CROP"); //剪裁
//                intent.setDataAndType(imageUri, "image/*");
//                intent.putExtra("scale", true);
//                //设置宽高比例
//                intent.putExtra("aspectX", 1);
//                intent.putExtra("aspectY", 1);
//                //设置裁剪图片宽高
//                intent.putExtra("outputX", 450);
//                intent.putExtra("outputY", 450);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                //广播刷新相册
                try {
                    bitmap = BitmapFactory.decodeStream(
                            getContentResolver().openInputStream(imageUri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                subImg1.setImageBitmap(bitmap);
                break;

        }
    }

}