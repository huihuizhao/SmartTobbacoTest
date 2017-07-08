package com.esri.arcgisruntime.sample.smarttobacco;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//import com.yuhj.ontheway.R;

public class DisasterActivity01 extends Activity implements OnItemClickListener {
    private TextView coordinatesTextView;
//    private ImageView imageButton;
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

    //动态获取权限监听
    private static PermissionListener mListener;

    private final static int TAKEPHOTO = 100;
    private final static int CHECKPHOTO = 200;
    private final static int CROPPHOTO = 300;

    private final static int TAKEPHOTO7 = 1007;
    private final static int CHECKPHOTO7 = 2007;
    private final static int CROPPHOTO7 = 3007;
    private String fileprovider = "com.esri.arcgisruntime.sample.smarttobacco.fileprovider";
    private String mPath;
    private Uri imageUri;
    private File cacheFile;
    private String cachPath;
    final int TARGET_HEAD_SIZE = 150;
    private ImageView disasterImageView01;
    private ImageView disasterImageView02;
    private ImageView disasterImageView03;
    private ImageView disasterImageView04;
    private int clickedImageViewNumber = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_disaster);
        TextView mTitleView = (TextView) findViewById(R.id.title_text);
        mTitleView.setText("灾害上报");
        disasterImageView01 = (ImageView) findViewById(R.id.disasterImageView01);
        disasterImageView02 = (ImageView) findViewById(R.id.disasterImageView02);
        disasterImageView03 = (ImageView) findViewById(R.id.disasterImageView03);
        disasterImageView04 = (ImageView) findViewById(R.id.disasterImageView04);
        //        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                1);
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//

//        int TAKE_PHOTO_REQUEST_CODE = 1;
//        if (ContextCompat.checkSelfPermission(TransplantActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(TransplantActivity.this, new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO_REQUEST_CODE);
//        }


        //判断是否开户相册权限
//        if (PackageManager.PERMISSION_GRANTED ==   ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)) {
//
//            Camera.startCameraUrl(context, filename, CAMERA);
//        }else{
        //提示用户开户权限
//        int RESULT_CODE_STARTCAMERA = 1;
//        String[] perms = {"android.permission.CAMERA"};
//        ActivityCompat.requestPermissions(TransplantActivity.this, perms, RESULT_CODE_STARTCAMERA);
//        }


//        TextView mTitleView = (TextView) findViewById(R.id.title_text);
//        mTitleView.setText("种植");

        coordinatesTextView = (TextView) findViewById(R.id.textViewCoordinates);
//		String coordinares = GetCoordinates();
        String coordinares = "";
        coordinatesTextView.setText(coordinares);



        OnClickListener ImageViewClickListener01 = new OnClickListener() {
            public void onClick(View v) {
//                InitiateDisplay("leftBottom");
//                imageButton = (ImageButton) findViewById(R.id.ImageButtonLeftBottom);
                StartCamara();
            }
        };
        disasterImageView01.setOnClickListener(ImageViewClickListener01);

        OnClickListener ImageViewClickListener02 = new OnClickListener() {
            public void onClick(View v) {
//                InitiateDisplay("leftBottom");
//                imageButton = (ImageButton) findViewById(R.id.ImageButtonLeftBottom);
                StartCamara();
            }
        };
        disasterImageView02.setOnClickListener(ImageViewClickListener02);
        OnClickListener ImageViewClickListener03 = new OnClickListener() {
            public void onClick(View v) {
//                InitiateDisplay("leftBottom");
//                imageButton = (ImageButton) findViewById(R.id.ImageButtonLeftBottom);
                StartCamara();
            }
        };
        disasterImageView03.setOnClickListener(ImageViewClickListener03);
        OnClickListener ImageViewClickListener04 = new OnClickListener() {
            public void onClick(View v) {
//                InitiateDisplay("leftBottom");
//                imageButton = (ImageButton) findViewById(R.id.ImageButtonLeftBottom);
                StartCamara();
            }
        };
        disasterImageView04.setOnClickListener(ImageViewClickListener04);


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


                Toast.makeText(DisasterActivity01.this, "已发送",
                        Toast.LENGTH_LONG).show();

                DisasterActivity01.this.finish();

            }

        };
        submitButton.setOnClickListener(submitClickListener);

    }

    // 显示对话框
    private void showProgressDialog() {
        dialog = new ProgressDialog(DisasterActivity01.this);
        dialog.setMessage("上传中...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    // 处理消息，让主界面提示上传成功
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MyToast.showToast(DisasterActivity01.this, "上传成功");
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
            AlertDialog.Builder builder = new AlertDialog.Builder(DisasterActivity01.this);
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
//        if (resultCode == RESULT_OK && requestCode == CAPTURE_PIC) {
//            Options options = new Options();
//            options.inJustDecodeBounds = true;// 设置解码只是为了获取图片的width和height值,而不是真正获取图片
//            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath, options);// 解码后可以options.outWidth和options.outHeight来获取图片的尺寸
//
//            int widthRatio = (int) Math.ceil(options.outWidth / width);// 获取宽度的压缩比率
//            int heightRatio = (int) Math.ceil(options.outHeight / height);// 获取高度的压缩比率
//
//            if (widthRatio > 1 || heightRatio > 1) {// 只要其中一个的比率大于1,说明需要压缩
//                if (widthRatio >= heightRatio) {// 取options.inSampleSize为宽高比率中的最大值
//                    options.inSampleSize = widthRatio;
//                } else {
//                    options.inSampleSize = heightRatio;
//                }
//            }
//
//            options.inJustDecodeBounds = false;// 设置为真正的解码图片
//            bitmap = BitmapFactory.decodeFile(imageFilePath, options);// 解码图片
//
//            imageButton.setImageBitmap(rebuildPicture(bitmap, 120, 100));
//        }
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
//                case TAKEPHOTO:
//                    startPhotoZoom(Uri.fromFile(new File(mPath + ".jpg")), TARGET_HEAD_SIZE);
//                    break;
//                case CHECKPHOTO:
//                    Uri uri = data.getData();
//                    startPhotoZoom(uri, TARGET_HEAD_SIZE);
//                    break;
                case CROPPHOTO:
                    Bitmap bm = data.getParcelableExtra("data");
                    disasterImageView01.setImageBitmap(bm);
                    break;

                case TAKEPHOTO7:
                    try {
                        // 将拍摄的照片显示出来
                        startPhotoZoom(new File(mPath + ".jpg"), 350);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case CHECKPHOTO7:
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                    break;
                case CROPPHOTO7:
                    try {
                        if (resultCode == RESULT_OK) {
                            Bitmap bitmap = BitmapFactory.decodeStream(
                                    getContentResolver().openInputStream(Uri.fromFile(new File(cachPath))));
                            disasterImageView01.setImageBitmap(bitmap);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }


    }


    @TargetApi(19)
    private String uriToPath(Uri uri) {
        String path = null;
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                path = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri =
                        ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                                Long.valueOf(docId));
                path = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            path = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            path = uri.getPath();
        }
        return path;
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                        new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver()
                        .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        String imagePath = uriToPath(uri);
        //        displayImage(imagePath); // 根据图片路径显示图片

        Log.i("TAG", "file://" + imagePath + "选择图片的URI" + uri);
        startPhotoZoom(new File(imagePath), 350);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        //        displayImage(imagePath);
        Log.i("TAG", "file://" + imagePath + "选择图片的URI" + uri);
        startPhotoZoom(new File(imagePath), 350);
    }


    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }


    /**
     * 剪裁图片
     */
    private void startPhotoZoom(File file, int size) {
        //Log.i("TAG", getImageContentUri(this, file) + "裁剪照片的真实地址");
        try {
            cachPath = FileUtils.getStorageDirectory() + UUID.randomUUID().toString() + ".jpg";
            cacheFile = new File(cachPath);
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(getImageContentUri(this, file), "image/*");//自己使用Content Uri替换File Uri
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", size);
            intent.putExtra("outputY", size);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", false);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cacheFile));//定义输出的File Uri
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", true);
            startActivityForResult(intent, CROPPHOTO7);
        } catch (ActivityNotFoundException e) {
            String errorMessage = "Your device doesn't support the crop action!";
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void StartCamara() {
        //判断是否开户相册权限
//        Toast.makeText(this, "准备启动相机", Toast.LENGTH_LONG).show();

        if (ActivityCompat.checkSelfPermission(DisasterActivity01.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "请开启应用拍照权限", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(DisasterActivity01.this, new String[]{Manifest.permission.CAMERA}, 1);
        }
        if (ActivityCompat.checkSelfPermission(DisasterActivity01.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "已经开启存储权限", Toast.LENGTH_LONG).show();
//            ActivityCompat.requestPermissions(TransplantActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},140);
        }

        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(DisasterActivity01.this, Manifest.permission.CAMERA)) {
//            Toast.makeText(this, "已经开启相机权限", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 相机捕捉图片的意图
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);// 指定系统相机拍照保存在imageFileUri所指的位置
//            startActivityForResult(intent, CAPTURE_PIC);// 启动系统相机,等待返回

            String[] permissions = {
                    Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            requestRuntimePermission(permissions, new PermissionListener() {
                @Override
                public void onGranted() {
                    myTakePhotoFor7();
                }

                @Override
                public void onDenied(List<String> deniedPermission) {
                    Toast.makeText(DisasterActivity01.this, deniedPermission.toString() + "权限被拒绝", Toast.LENGTH_LONG).show();
                    //有权限被拒绝，什么也不做好了，看你心情
                }
            });


        } else {
//            提示用户开户权限
//            Toast.makeText(this, "请开启应用拍照权限", Toast.LENGTH_LONG).show();
            int RESULT_CODE_STARTCAMERA = 1;
            String[] perms = {"android.permission.CAMERA"};
            ActivityCompat.requestPermissions(DisasterActivity01.this, perms, RESULT_CODE_STARTCAMERA);
        }


//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 相机捕捉图片的意图
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);// 指定系统相机拍照保存在imageFileUri所指的位置
//        startActivityForResult(intent, CAPTURE_PIC);// 启动系统相机,等待返回
    }

    //andrpoid 6.0 需要写运行时权限
    public void requestRuntimePermission(String[] permissions, PermissionListener listener) {

        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(DisasterActivity01.this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(DisasterActivity01.this,
                    permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            mListener.onGranted();
        }
    }

    private void myTakePhotoFor7() {
//        String mUUID = UUID.randomUUID().toString();
//        mPath = FileUtils.getStorageDirectory() + mUUID;
        mPath = FileUtils.getStorageDirectory() + "leftTop";

        File cameraFile = new File(mPath + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            imageUri = Uri.fromFile(cameraFile);
        } else {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            imageUri = FileProvider.getUriForFile(DisasterActivity01.this, fileprovider,
                    cameraFile);
        }
        // 启动相机程序
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKEPHOTO7);
    }

    public interface PermissionListener {
        /**
         * 成功获取权限
         */
        void onGranted();

        /**
         * 为获取权限
         */
        void onDenied(List<String> deniedPermission);
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
                        DisasterActivity01.this,
                        "正在上传第" + String.valueOf(i) + "张(共" + photoCount
                                + "张)...", Toast.LENGTH_LONG).show();
                Thread thread = new SendFileThread(stream.toByteArray());
                thread.start();

                Toast.makeText(
                        DisasterActivity01.this,
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
