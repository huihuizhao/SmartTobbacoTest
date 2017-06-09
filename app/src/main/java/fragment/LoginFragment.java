package fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

import com.esri.arcgisruntime.sample.smarttobacco.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



@SuppressLint("ValidFragment")
public class LoginFragment extends Fragment {

    private View dictView;
    private Context context;
    private ViewPager viewPager;
    private List<View> views;
    private ScheduledExecutorService scheduledExecutorService;
    private int currentItem = 0;

    private Display display;
    private Timer timer;
    //	private PullToRefreshScrollView mPullRefreshScrollView;
    private ScrollView mScrollView;
    private LinearLayout line_voice;




    public LoginFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dictView = inflater.inflate(R.layout.login, container,
                false);
        display = getActivity().getWindowManager().getDefaultDisplay();


        viewPager = (ViewPager) dictView.findViewById(R.id.myViewPager);

        int height = display.getHeight() / 3;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.FILL_PARENT, height);
        viewPager.setLayoutParams(params);


        return dictView;
    }



    @Override
    public void onStart() {
        // TODO 自动生成的方法存根

    }

    @Override
    public void onResume() {
        // TODO 自动生成的方法存根
        super.onResume();
    }

    @Override
    public void onPause() {
        // TODO 自动生成的方法存根
        super.onPause();
    }

    @Override
    public void onDestroy() {
        // TODO 自动生成的方法存根
        super.onDestroy();
    }



    @Override
    public void onStop() {
        // TODO 自动生成的方法存根

        scheduledExecutorService.shutdown();
        super.onStop();
    }




}
