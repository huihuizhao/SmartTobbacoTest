package com.esri.arcgisruntime.sample.smarttobacco;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

//import com.yuhj.ontheway.R;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class TransplantActivity extends Activity implements OnItemClickListener {
    private TextView coordinatesTextView;
    private ImageView imageButton;
    private int width;
    private int height;
    private String imageFilePath;
    private Uri imageFileUri;
    private static final int CAPTURE_PIC = 0;
    private Button submitButton;

    private String urlParameters = "";
    private String url_constant_parameters = "";
    private String uploadServerUrl = "";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_transplant);

        TextView mTitleView = (TextView) findViewById(R.id.title_text);
        mTitleView.setText("种植");

        coordinatesTextView = (TextView) findViewById(R.id.textViewCoordinates);
//		String coordinares = GetCoordinates();
        String coordinares = "";
        coordinatesTextView.setText(coordinares);

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButtonLeftTop);
        OnClickListener ClickListener1 = new OnClickListener() {
            public void onClick(View v) {
                InitiateDisplay("leftTop");
                imageButton = (ImageButton) findViewById(R.id.imageButtonLeftTop);
                StartCamara();
            }
        };
        imageButton1.setOnClickListener(ClickListener1);

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.ImageButtonRightTop);
        OnClickListener ClickListener2 = new OnClickListener() {
            public void onClick(View v) {
                InitiateDisplay("rightTop");
                imageButton = (ImageButton) findViewById(R.id.ImageButtonRightTop);
                StartCamara();
            }
        };
        imageButton2.setOnClickListener(ClickListener2);

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.ImageButtonRightBottom);
        OnClickListener ClickListener3 = new OnClickListener() {
            public void onClick(View v) {
                InitiateDisplay("rightBottom");
                imageButton = (ImageButton) findViewById(R.id.ImageButtonRightBottom);
                StartCamara();
            }
        };
        imageButton3.setOnClickListener(ClickListener3);

        ImageButton imageButton4 = (ImageButton) findViewById(R.id.ImageButtonLeftBottom);
        OnClickListener ClickListener4 = new OnClickListener() {
            public void onClick(View v) {
                InitiateDisplay("leftBottom");
                imageButton = (ImageButton) findViewById(R.id.ImageButtonLeftBottom);
                StartCamara();
            }
        };
        imageButton4.setOnClickListener(ClickListener4);

        submitButton = (Button) findViewById(R.id.SubmitButton);
        OnClickListener submitClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // ArrayList<String> imagePathArrayList = new
                // ArrayList<String>();
                // imagePathArrayList.add(Environment.getExternalStorageState()
                // .equals(Environment.MEDIA_MOUNTED) ? Environment
                // .getExternalStorageDirectory() + "/" + "leftTop.jpg"
                // : null);
                // imagePathArrayList.add(Environment.getExternalStorageState()
                // .equals(Environment.MEDIA_MOUNTED) ? Environment
                // .getExternalStorageDirectory() + "/" + "rightTop.jpg"
                // : null);
                // imagePathArrayList.add(Environment.getExternalStorageState()
                // .equals(Environment.MEDIA_MOUNTED) ? Environment
                // .getExternalStorageDirectory()
                // + "/"
                // + "rightBottom.jpg" : null);
                // imagePathArrayList.add(Environment.getExternalStorageState()
                // .equals(Environment.MEDIA_MOUNTED) ? Environment
                // .getExternalStorageDirectory() + "/" + "leftBottom.jpg"
                // : null);
                //
                // SendFile(imagePathArrayList);
                String ip = "192.168.1.101";

                urlParameters = "http://" + ip + ":8080/SmartTobaccoWeb/Insert.action?";
                url_constant_parameters = "http://" + ip
                        + ":8080/SmartTobaccoWeb/Insert.action?";
                uploadServerUrl = "http://" + ip
                        + ":8080/SmartTobaccoWeb/UploadServlet?";

                showProgressDialog();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //Your code goes here
//                            String imageFile1 = Environment.getExternalStorageState().equals(
//                                    Environment.MEDIA_MOUNTED) ? Environment
//                                    .getExternalStorageDirectory() + "/" + "leftTop" + ".jpg" : null;
////                            File file = new File("/storage/emulated/0/leftTop.jpg");
//                            File file1 = new File(imageFile1);
//                            UploadUtil.uploadFile(file1, "http://pic.giscloud.ac.cn");
//                            File file2 = new File("/storage/emulated/0/rightTop.jpg");
//                            UploadUtil.uploadFile(file2, "http://pic.giscloud.ac.cn");
//                            File file3 = new File("/storage/emulated/0/leftBottom.jpg");
//                            UploadUtil.uploadFile(file3, "http://pic.giscloud.ac.cn");
//                            File file4 = new File("/storage/emulated/0/rightBottom.jpg");
//                            UploadUtil.uploadFile(file4, "http://pic.giscloud.ac.cn");


                            String ID = "1";
                            String area = "10";
                            String count = "10";
                            String longitude = "10";
                            String latitude = "10";
                            String date = "10";
                            String fieldID = "10";
                            String town = "10";
                            String picturePath = "10";

                            // 上传数据库表格字段信息
                            InsertToDatabaseService(ID, area, count, longitude, latitude, date, fieldID, town, picturePath);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();


                Toast.makeText(TransplantActivity.this, "已发送",
                        Toast.LENGTH_LONG).show();

                TransplantActivity.this.finish();

            }

        };
        submitButton.setOnClickListener(submitClickListener);

    }

    // 显示对话框
    private void showProgressDialog() {
        dialog = new ProgressDialog(TransplantActivity.this);
        dialog.setMessage("上传中...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    // 处理消息，让主界面提示上传成功
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MyToast.showToast(TransplantActivity.this, "上传成功");
        }
    };

    /**
     * 获取Struts2 Http 插入数据的请求信息
     *
     * @param userName
     * @param password
     */
    public void InsertToDatabaseService(String ID, String area, String count,
                                        String longitude, String latitude, String date, String fieldID, String town, String picturePath) {
        // public void loginRemoteService(String userName, String password) {
        String result = null;
        try {

            // 创建一个HttpClient对象

            HttpClient httpclient = new DefaultHttpClient();
            // 远程登录URL
            // 下面这句是原有的
            // processURL=processURL+"userName="+userName+"&password="+password;
            urlParameters = url_constant_parameters + "ID=" + ID + "area=" + area + "&count=" + count + "&longitude=" + longitude
                    + "&latitude=" + latitude + "&date=" + date + "&fieldID=" + fieldID + "&town=" + town + "&picturePath=" + picturePath;
            Log.d("远程URL", urlParameters);
            // 创建HttpGet对象
            HttpGet request = new HttpGet(urlParameters);
            // 请求信息类型MIME每种响应类型的输出（普通文本、html 和 XML，json）。允许的响应类型应当匹配资源类中生成的 MIME
            // 类型
            // 资源类生成的 MIME 类型应当匹配一种可接受的 MIME 类型。如果生成的 MIME 类型和可接受的 MIME 类型不
            // 匹配，那么将
            // 生成 com.sun.jersey.api.client.UniformInterfaceException。例如，将可接受的
            // MIME 类型设置为 text/xml，而将
            // 生成的 MIME 类型设置为 application/xml。将生成 UniformInterfaceException。
            request.addHeader("Accept", "text/json");
            // 获取响应的结果
            HttpResponse response = httpclient.execute(request);
            // 获取HttpEntity
            HttpEntity entity = response.getEntity();
            // 获取响应的结果信息
            String json = EntityUtils.toString(entity, "UTF-8");
            // JSON的解析过程
            if (json != null) {
                JSONObject jsonObject = new JSONObject(json);
                result = jsonObject.get("message").toString();
                dialog.dismiss();
                Message msg = new Message();
                handler.sendMessage(msg);

            }
            if (result == null) {
                json = "登录失败请重新登录";
            }
            // 创建提示框提醒是否登录成功
            AlertDialog.Builder builder = new AlertDialog.Builder(TransplantActivity.this);
            builder.setTitle("提示")
                    .setMessage(result)
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.dismiss();
                                }
                            }).create().show();

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.transplant, menu);
        return true;
    }

    // public String GetCoordinates()
    // {
    // String timerGpsInfo = "0.0000,0.0000";
    // BDLocation location = MainActivity.locationClient
    // .getLastKnownLocation();
    // if (location != null)
    // {
    // if (location.getLocType() == BDLocation.TypeGpsLocation
    // || location.getLocType() == BDLocation.TypeNetWorkLocation
    // || location.getLocType() == BDLocation.TypeCacheLocation)
    // {
    // timerGpsInfo = location.getLatitude() + ","
    // + location.getLongitude();
    // }
    // }
    //
    // return timerGpsInfo;
    // }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Map<String, Object> map = (Map<String, Object>) parent
                .getItemAtPosition(position);
        String itemName = (String) map.get("itemName");

        // if (itemName == "����")
        // {
        // Intent transplantIntent = new Intent(this, TransplantActivity.class);
        // startActivity(transplantIntent);
        // }

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

            options.inJustDecodeBounds = false;// 设置为真正的解码图片
            bitmap = BitmapFactory.decodeFile(imageFilePath, options);// 解码图片

            imageButton.setImageBitmap(rebuildPicture(bitmap, 120, 100));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void InitiateDisplay(String place) {
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        imageFilePath = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED) ? Environment
                .getExternalStorageDirectory() + "/" + place + ".jpg" : null;
        imageFileUri = Uri.fromFile(new File(imageFilePath));
    }

    private void StartCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 相机捕捉图片的意图
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);// 指定系统相机拍照保存在imageFileUri所指的位置
        startActivityForResult(intent, CAPTURE_PIC);// 启动系统相机,等待返回
    }

    private void SendFile(ArrayList<String> filepaths) {
        int i = 1;
        int photoCount = filepaths.size();
        for (String photoPath : filepaths) {
            if (photoPath != null) {
                Bitmap photo;

                Options options = new Options();
                options.inJustDecodeBounds = true;

                Bitmap bit = BitmapFactory.decodeFile(photoPath, options);

                // 重采样为原来的1/4
                // options.inSampleSize = 4;
                /* 这样才能真正的返回一个Bitmap给你 */
                options.inJustDecodeBounds = false;
                photo = BitmapFactory.decodeFile(photoPath, options);
                Bitmap newBitmap = rebuildPicture(photo, 800, 600);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);// (0

                Toast.makeText(
                        TransplantActivity.this,
                        "正在上传第" + String.valueOf(i) + "张(共" + photoCount
                                + "张)...", Toast.LENGTH_LONG).show();
                Thread thread = new SendFileThread(stream.toByteArray());
                thread.start();

                Toast.makeText(
                        TransplantActivity.this,
                        "第" + String.valueOf(i) + "张上传完成(共" + photoCount
                                + "张)...", Toast.LENGTH_LONG).show();
                i++;
                photoPath = null;
                // btnUpload.setEnabled(false);
            } else {
                Toast.makeText(this, "上传的文件路径出错", Toast.LENGTH_LONG).show();
            }

        }

    }

    public class SendFileThread extends Thread {
        private byte[] byteArrayPicture;

        public SendFileThread(byte[] bytesPicture) {
            byteArrayPicture = new byte[bytesPicture.length];
            System.arraycopy(bytesPicture, 0, byteArrayPicture, 0,
                    bytesPicture.length);
        }

        public void run() {
            try {
                // 将图像数据通过Socket发送出去
                String Head = "11," + "," + "1," + "16,";// 命令编码+坐标X+坐标Y+事件编号+图层编号+状态编码
                // String Head = "11," + PicGpsInfo + ",";
                byte[] byteHead = Head.getBytes("UTF-8");
                byte[] bytesOutput = new byte[byteHead.length
                        + byteArrayPicture.length];
                System.arraycopy(byteHead, 0, bytesOutput, 0, byteHead.length);
                System.arraycopy(byteArrayPicture, 0, bytesOutput,
                        byteHead.length, byteArrayPicture.length);
//				MainActivity.pSocketClient.sendServer(bytesOutput);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Bitmap rebuildPicture(Bitmap bit, int w, int h) {
        Bitmap resize = Bitmap.createScaledBitmap(bit, w, h, true);
        Matrix matrix = new Matrix();
        int width = resize.getWidth();
        int height = resize.getHeight();
        matrix.setRotate(90);
        Bitmap b = Bitmap.createBitmap(resize, 0, 0, width, height, matrix,
                true);
        return b;
    }

}
