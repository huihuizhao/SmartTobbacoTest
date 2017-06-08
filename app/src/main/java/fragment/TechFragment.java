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

//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
//import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
//import com.ssb.mdict.R;
//import com.ssb.mdict.db.ParseJson;
//import com.ssb.mdict.entity.ResultJson;
//import com.ssb.mdict.entity.Song;
//import com.ssb.mdict.ui.TranslateView;
//import com.ssb.mdict.util.ClientRequestOperation;

@SuppressLint("ValidFragment")
public class TechFragment extends Fragment {

    private View dictView;
    private Context context;
    private ViewPager viewPager;
    private List<View> views;
    private ScheduledExecutorService scheduledExecutorService;
    private int currentItem = 0;

    private ImageView point;
    private ListView song_grid;
    private int[] images = {R.drawable.techslideaa, R.drawable.techslideba,
            R.drawable.techslideca};
    private int[] points = {R.drawable.point01, R.drawable.point02,
            R.drawable.point03};
//	private List<Song> list_song = null;
    /**
     *
     */
    private Display display;
    private Timer timer;
    //	private PullToRefreshScrollView mPullRefreshScrollView;
    private ScrollView mScrollView;
    private LinearLayout line_voice;

    private final int SET_PAGER_ITEM = 1001;
    private final int TO_UP = 1002;
    private final int SET_CLASS_TRANSLATE = 1003;

    private ListView mListView;
    private SimpleAdapter simpleAdapter;


    public TechFragment(Context context) {
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
        dictView = inflater.inflate(R.layout.activity_techfragment, container,
                false);
        display = getActivity().getWindowManager().getDefaultDisplay();
        initViewPager(inflater);
        initListView(inflater);
        initScrollView();
//		myCreate();


        return dictView;
    }

    private void initViewPager(LayoutInflater inflater) {

        viewPager = (ViewPager) dictView.findViewById(R.id.myViewPager);
        point = (ImageView) dictView.findViewById(R.id.point);
        int height = display.getHeight() / 3;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.FILL_PARENT, height);
        viewPager.setLayoutParams(params);
        views = new ArrayList<View>();
        for (int i = 0; i < images.length; i++) {

            ImageView imageView = new ImageView(context);
            imageView.setImageResource(images[i]);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            views.add(imageView);
        }

        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
//        line_voice = (LinearLayout) dictView.findViewById(R.id.line_voice);
//        line_voice.setOnClickListener(new MyOnClick(SET_CLASS_TRANSLATE));
        // timer = new Timer();
        // setTimerTask();


        mListView = (ListView) dictView.findViewById(R.id.news_list);

        //创建简单适配器SimpleAdapter
        simpleAdapter = new SimpleAdapter(getActivity(), this.getItem(), R.layout.listviewitem,
                new String[]{"itemTitle", "itemPhoto", "itemSummary", "itemAuthor", "itemPublishtime"},
                new int[]{R.id.title, R.id.photograph, R.id.summary, R.id.publishtime});

        //加载SimpleAdapter到ListView中
        mListView.setAdapter(simpleAdapter);

    }

    public ArrayList<HashMap<String, Object>> getItem() {
        ArrayList<HashMap<String, Object>> item = new ArrayList<HashMap<String, Object>>();
//        for (int i = 0; i < 2; i++) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("itemTitle", "烟草炭疽病");
        map.put("itemPhoto", R.drawable.techimageaa);
        map.put("itemSummary", "烟草炭疽病由属半知菌亚六包盘孢属的真菌侵染引起的。烟草炭疽病于1922年由巴西首次报道以后，德国、日本、美国、中国、澳大利亚、印度、朝鲜和非洲也相继发现。此病在烟草各生育期皆可发生，但以苗期发生普遍而严重。幼苗叶片病斑密布，严重发病时往往使整片烟苗毁掉，一般发病时虽不至于苗毁，但幼苗生势差，而且移栽大田后仍可继续为害，招致较大损失。");
        map.put("itemPublishtime", "2017年05月25日");
        item.add(map);

        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("itemTitle", "烟草天蛾");
        map2.put("itemPhoto", R.drawable.techimageba);
        map2.put("itemSummary", "  烟草天蛾（学名：Manduca sexta），又名传粉夜蛾，是天蛾科的一种蛾，在美洲大陆大部分地区可见。它很容易与番茄天蛾（Manduca quinquemaculata）混淆。两者长相相似，且属于同一属。其幼虫都以茄科植物的叶子为食。烟草天蛾两侧有7对斜线，番茄天蛾则有8个V形图案。烟草天蛾身体有一种机制，选择性地吸收和分泌烟草中的神经毒素尼古丁。");
        map2.put("itemPublishtime", "2017年05月17日");
        item.add(map2);
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("itemTitle", "烟草野火病和角斑病怎么防治?");
        map3.put("itemPhoto", R.drawable.techimageca);
        map3.put("itemSummary", "    烟草野火病与角斑病是烟草上的重要病害,两种病在病源菌特性、侵染规律以及防治方法等方面都很相似.在田间这两种细菌性病害经常混合发生,在全国各烟区发生较为普遍.野火病在黑龙江、辽宁、山东、云南、四川、陕西等省发生较重,而角斑病在山东、福建、河南、贵州、吉林、云南较重。 ");
        map3.put("itemPublishtime", "2017年05月16日");
        item.add(map3);




//        }
        return item;
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1));
            return views.get(arg1);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }

    private class MyPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO 自动生成的方法存根
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO 自动生成的方法存根

        }

        @Override
        public void onPageSelected(int arg0) {
            // TODO 自动生成的方法存根
            point.setImageResource(points[arg0]);
        }
    }

    @Override
    public void onStart() {
        // TODO 自动生成的方法存根

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 3,
                TimeUnit.SECONDS);
        super.onStart();
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

    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % views.size();
                sendMessage(SET_PAGER_ITEM); // 通过Handler切换图片
            }
        }
    }

    @Override
    public void onStop() {
        // TODO 自动生成的方法存根

        scheduledExecutorService.shutdown();
        super.onStop();
    }

    // private void setTimerTask() {
    //
    // timer.schedule(new TimerTask() {
    // @Override
    // public void run() {
    // sendMessage(1001);
    // }
    // }, 3000, 3000/* 表示3000毫秒之後，每隔3000毫秒執行一次 */);
    // }

    private Handler doActionHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SET_PAGER_ITEM:

                    viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
                    break;

                case TO_UP:

//				mScrollView.fullScroll(ScrollView.FOCUS_UP);
                    break;

                default:
                    break;
            }
        }
    };

    private void sendMessage(int what) {

        Message message = doActionHandler.obtainMessage();
        message.what = what;
        message.sendToTarget();
    }

    private class MyOnClick implements OnClickListener {

        int index;

        public MyOnClick(int i) {
            index = i;
        }

        @Override
        public void onClick(View arg0) {
            switch (index) {

                case SET_CLASS_TRANSLATE:

                    Intent intent = new Intent();
//				intent.setClass(context, TranslateView.class);
//				startActivity(intent);

                    break;

                default:
                    break;
            }
        }
    }

    private void initListView(LayoutInflater inflater) {

        song_grid = (ListView) dictView.findViewById(R.id.song_grid);
//		Song song = new Song();
//		list_song = song.getSongs();
//		SongGridApapter songGridApapter = new SongGridApapter(inflater,
//				list_song, context);
//		song_grid.setAdapter(songGridApapter);
        setListViewHeightBasedOnChildren(song_grid);
    }

    private void setListViewHeightBasedOnChildren(ListView song_grid) {

//        ListAdapter listAdapter = song_grid.getAdapter();
//        if (listAdapter == null) {
//            return;
//        }
//
//        int totalHeight = 0;
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            View listItem = listAdapter.getView(i, null, song_grid);
//            listItem.measure(0, 0);
//            totalHeight += listItem.getMeasuredHeight();
//        }
//
//        ViewGroup.LayoutParams params = song_grid.getLayoutParams();
//        params.height = totalHeight
//                + (song_grid.getDividerHeight() * (listAdapter.getCount() - 1));
//        song_grid.setLayoutParams(params);
    }

    private void initScrollView() {

//		mPullRefreshScrollView = (PullToRefreshScrollView) dictView
//				.findViewById(R.id.pull_refresh_scrollview);
//		mPullRefreshScrollView
//				.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
//
//					@Override
//					public void onRefresh(
//							PullToRefreshBase<ScrollView> refreshView) {
//						new GetDataTask().execute();
//					}
//				});
//		mScrollView = mPullRefreshScrollView.getRefreshableView();
        sendMessage(TO_UP);
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            // Do some stuff here

            // Call onRefreshComplete when the list has been refreshed.
//			mPullRefreshScrollView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    private void myCreate() {
        //
        // spinner_dict = (Spinner) dictView.findViewById(R.id.spinner_dict);
        // String[] str_Languages = { "自动检测", "中-英", "英-中" };
        // List<DictSpinnerItem> dict_spinner_list = new
        // ArrayList<DictSpinnerItem>();
        // for (int i = 0; i < str_Languages.length; i++)
        // {
        // DictSpinnerItem DictSpinnerItem = new DictSpinnerItem();
        //
        // DictSpinnerItem.setLanguages(str_Languages[i]);
        // dict_spinner_list.add(DictSpinnerItem);
        // }
        // BaseAdapter distSpinnerAdapter = new
        // DistSpinnerAdapter(getActivity(),
        // dict_spinner_list);
        // if (spinner_dict != null)
        // {
        // spinner_dict.setAdapter(distSpinnerAdapter);
        // }
        // edit_dict = (EditText) dictView.findViewById(R.id.edit_dict_write);
        // text_show_dict = (TextView)
        // dictView.findViewById(R.id.text_show_dict);
        //
        // button_dict_search = (Button)
        // dictView.findViewById(R.id.button_dict_search);
        // if (button_dict_search != null)
        // {
        // button_dict_search.setOnClickListener(new OnClickListener()
        // {
        //
        // @Override
        // public void onClick(View v)
        // {

        new Thread() {

            @Override
            public void run() {

                // MyHttp myHttp = new MyHttp(dict_write);
                // try
                // {
                // result = myHttp.getTransResult();
                // sendMessage(1001);
                // }
                // catch (JSONException e)
                // {
                // e.printStackTrace();
                // }
//				String dict_write02 = ClientRequestOperation.encode("吐");
//				String url = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=q0Hf2pchsKTZtFMsdSolj1MG&q="
//						+ dict_write02 + "&from=auto&to=auto";
//				ParseJson parseJson = new ParseJson(
//						ClientRequestOperation.executeHttpGet(url));
//				try {
//					ResultJson result = parseJson.Json();
//
//					System.out.println("------你懂的----"+result.getList_char().get(0).getSrc());
//				} catch (JSONException e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}

                super.run();
            }
        }.start();
    }
    // private Handler handler = new Handler(new Handler.Callback()
    // {
    //
    // public boolean handleMessage(Message msg)
    // {
    //
    // switch (msg.what) {
    //
    // case 1001:
    //
    // if (result != null)
    // {
    // Log.v("ssb", "********" + result.getList_char().get(0).getDst());
    // text_show_dict.setText(result.getList_char().get(0).getDst());
    // }
    //
    // break;
    // }
    // return false;
    // };
    // });

    // private void sendMessage(int msg)
    // {
    //
    // Message message = handler.obtainMessage();
    // message.what = msg;
    // message.sendToTarget();
    // }
}
