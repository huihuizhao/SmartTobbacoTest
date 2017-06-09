package com.esri.arcgisruntime.sample.smarttobacco;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

//import com.yuhj.ontheway.R;

public class NewsDetailActivity extends Activity implements OnItemClickListener {
    private TextView coordinatesTextView;
    private ImageView imageButton;
    private int width;
    private int height;
    private String imageFilePath;
    private Uri imageFileUri;
    private static final int CAPTURE_PIC = 0;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_transplant);

        TextView mTitleView = (TextView) findViewById(R.id.title_text);
        mTitleView.setText("种植");

        submitButton = (Button) findViewById(R.id.SubmitButton);
        OnClickListener submitClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //Your code goes here
                            String imageFile1 = Environment.getExternalStorageState().equals(
                                    Environment.MEDIA_MOUNTED) ? Environment
                                    .getExternalStorageDirectory() + "/" + "leftTop" + ".jpg" : null;
//                            File file = new File("/storage/emulated/0/leftTop.jpg");
                            File file1 = new File(imageFile1);
                            UploadUtil.uploadFile(file1, "http://pic.giscloud.ac.cn");
                            File file2 = new File("/storage/emulated/0/rightTop.jpg");
                            UploadUtil.uploadFile(file2, "http://pic.giscloud.ac.cn");
                            File file3 = new File("/storage/emulated/0/leftBottom.jpg");
                            UploadUtil.uploadFile(file3, "http://pic.giscloud.ac.cn");
                            File file4 = new File("/storage/emulated/0/rightBottom.jpg");
                            UploadUtil.uploadFile(file4, "http://pic.giscloud.ac.cn");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();


                Toast.makeText(NewsDetailActivity.this, "已发送",
                        Toast.LENGTH_LONG).show();

                NewsDetailActivity.this.finish();

            }

        };
        submitButton.setOnClickListener(submitClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.transplant, menu);
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Map<String, Object> map = (Map<String, Object>) parent
                .getItemAtPosition(position);
        String itemName = (String) map.get("itemName");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == CAPTURE_PIC) {
            Options options = new Options();
            options.inJustDecodeBounds = true;// 设置解码只是为了获取图片的width和height值,而不是真正获取图片
            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath, options);// 解码后可以options.outWidth和options.outHeight来获取图片的尺寸

            int widthRatio = (int) Math.ceil(options.outWidth / width);// 获取宽度的压缩比率
            int heightRatio = (int) Math.ceil(options.outHeight / height);// 获取高度的压缩比率

            if (widthRatio > 1 || heightRatio > 1) {// 只要其中一个的比率大于1,说明需要压缩
                if (widthRatio >= heightRatio) {// 取options.inSampleSize为宽高比率中的最大值
                    options.inSampleSize = widthRatio;
                } else {
                    options.inSampleSize = heightRatio;
                }
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }






}
