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

public class DisasterActivity00 extends Activity implements OnItemClickListener {
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
		setContentView(R.layout.activity_disaster);

		TextView mTitleView = (TextView) findViewById(R.id.title_text);
		mTitleView.setText("灾害上报");

		coordinatesTextView = (TextView) findViewById(R.id.textViewCoordinates);
//		String coordinares = GetCoordinates();
		String coordinares ="";
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

				Toast.makeText(DisasterActivity00.this, "已发送",
						Toast.LENGTH_LONG).show();

				DisasterActivity00.this.finish();

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
						DisasterActivity00.this,
						"正在上传第" + String.valueOf(i) + "张(共" + photoCount
								+ "张)...", Toast.LENGTH_LONG).show();
				Thread thread = new SendFileThread(stream.toByteArray());
				thread.start();

				Toast.makeText(
						DisasterActivity00.this,
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
