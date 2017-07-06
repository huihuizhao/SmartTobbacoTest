package com.esri.arcgisruntime.sample.smarttobacco;

import android.Manifest;
import android.os.Bundle;

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}


import java.util.ArrayList;

//        import R;

//        import fragment.HuodongFragment;
//        import fragment.JingXuanFragment;
//        import fragment.KnowledgeFragment;
//        import fragment.LoginFragment;
import fragment.HomeFragment;
import fragment.LoginFragment;
import fragment.MapFragment;
import fragment.TechFragment;
//import fragment.DictFragment;
//        import fragment.ZhuanTiFragment;
//        import com.zdp.aseo.content.AseoZdpAseo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * @author 赵辉辉
 * @version 1.0
 * @name MainActivity
 * @date 2016-10-22
 */
public class MainActivity extends FragmentActivity implements
        OnCheckedChangeListener {
    private TextView title;
    private Animation loadAnimation;
    //	private JingXuanFragment jingXuanFragment;

    private HomeFragment homeFragment;
    //    private PlantFragment plantFragment;
    private MapFragment mapFragment;
    private TechFragment techFragment;
    private LoginFragment loginFragment;

    //	private HuodongFragment huodongFragment;
//    private KnowledgeFragment knowledgeFragment;
    //	private ZhuanTiFragment zhuanTiFragment;
//    private DisasterFragment00 disasterFragment;
//    private LoginFragment loginFragment;
    private ArrayList<Fragment> fragments;
    private RadioGroup group;
    private RadioButton imageView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();

//        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                1);
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//        AseoZdpAseo.initTimer(this);
        //判断是否开户相册权限
//        if (PackageManager.PERMISSION_GRANTED ==   ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)) {
//
//            Camera.startCameraUrl(context, filename, CAMERA);
//        }else{
        //提示用户开户权限
//        int RESULT_CODE_STARTCAMERA = 1;
//        String[] perms = {"android.permission.CAMERA"};
//        ActivityCompat.requestPermissions(MainActivity.this, perms, RESULT_CODE_STARTCAMERA);
//        }


        group = (RadioGroup) findViewById(R.id.main_tab_bar);
        group.setOnCheckedChangeListener(this);
        fragments = new ArrayList<Fragment>();
//		fragments.add(jingXuanFragment);
//        fragments.add(plantFragment);
        fragments.add(homeFragment);
        fragments.add(mapFragment);
        fragments.add(techFragment);
        fragments.add(loginFragment);

//        fragments.add(dictFragment);


//        fragments.add(knowledgeFragment);
//        fragments.add(loginFragment);
        if (getIntent().getIntExtra("FragmentType", 0) == 3) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.main_framelayout, fragments.get(3));
            transaction.commit();
            title.setText("设置");
        } else {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.main_framelayout, fragments.get(0));
            transaction.commit();
            title.setText("种植");
        }


    }

    private void initViews() {
//		jingXuanFragment = new JingXuanFragment();
//        plantFragment = new PlantFragment(context);

        homeFragment = new HomeFragment(context);
        mapFragment = new MapFragment(context);
        techFragment = new TechFragment(context);
        loginFragment = new LoginFragment(context);

//        knowledgeFragment = new KnowledgeFragment(context);
//        loginFragment=new LoginFragment();
        title = (TextView) findViewById(R.id.main_title);
        imageView = (RadioButton) findViewById(R.id.main_add);
//        AseoZdpAseo.init(this, AseoZdpAseo.INSERT_TYPE);
        imageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                loadAnimation = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.layout.btn_add);
                imageView.startAnimation(loadAnimation);
                startActivity(new Intent(MainActivity.this, AddActivity.class));
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup view, int checkId) {
        int childCount = group.getChildCount();
        int checkedIndex = 0;
        RadioButton btnButton = null;
        for (int i = 0; i < childCount; i++) {
            btnButton = (RadioButton) group.getChildAt(i);
            if (btnButton.isChecked()) {
                checkedIndex = i;
                break;
            }
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = null;
        switch (checkedIndex) {
            case 0:
                fragment = fragments.get(0);
                transaction.replace(R.id.main_framelayout, fragment);
                transaction.commit();
                title.setText("种植");
                break;
            case 1:
                fragment = fragments.get(1);
                transaction.replace(R.id.main_framelayout, fragment);
                transaction.commit();
                title.setText("监测");
                break;
            case 2:

                break;
            case 3:
                fragment = fragments.get(2);
                transaction.replace(R.id.main_framelayout, fragment);
                transaction.commit();
                title.setText("科普");
                break;
            case 4:
                fragment = fragments.get(3);
                transaction.replace(R.id.main_framelayout, fragment);
                transaction.commit();
                title.setText("设置");
                break;

            default:
                break;
        }

    }

}
