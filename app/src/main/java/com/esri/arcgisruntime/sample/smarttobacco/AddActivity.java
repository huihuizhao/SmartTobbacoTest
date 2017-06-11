package com.esri.arcgisruntime.sample.smarttobacco;


import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author 禹慧军
 * @version 1.0
 * @name AddActivity
 * @Descripation 发布，上传<br>
 * @date 2014-10-24
 */
public class AddActivity extends FragmentActivity implements OnClickListener {
    private Button btnTransplant, btnGrow, btnDisaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initViews();
    }

    private void initViews() {
        btnTransplant = (Button) findViewById(R.id.select_picture);
        btnGrow = (Button) findViewById(R.id.take_photo);
        btnDisaster = (Button) findViewById(R.id.publish_text);
        btnTransplant.setOnClickListener(this);
        btnGrow.setOnClickListener(this);
        btnDisaster.setOnClickListener(this);
//		AseoZdpAseo.initType(this, AseoZdpAseo.SCREEN_TYPE);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        finish();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == btnTransplant) {
//			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//			startActivityForResult(intent, 1001);

            Intent transplantIntent = new Intent(this, TransplantActivity.class);
            startActivity(transplantIntent);
        } else if (view == btnDisaster) {
//			Intent intent = new Intent(AddActivity.this, MainActivity.class);
//			intent.putExtra("FragmentType", 3);
//			startActivity(intent);

            Intent transplantIntent = new Intent(this, DisasterActivity.class);
            startActivity(transplantIntent);
        } else if (view == btnGrow) {
//			Intent intent = new Intent();
//			intent.setType("image/*");
//			intent.setAction(Intent.ACTION_GET_CONTENT);
//			this.startActivityForResult(intent, 1002);

//			Intent transplantIntent = new Intent(this, TransplantActivity.class);
//			startActivity(transplantIntent);
            Intent transplantIntent = new Intent(this, GrowActivity.class);
            startActivity(transplantIntent);

        }

    }

}
