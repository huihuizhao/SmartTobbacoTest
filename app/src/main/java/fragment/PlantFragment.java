package fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.esri.arcgisruntime.sample.smarttobacco.R;
import com.esri.arcgisruntime.sample.smarttobacco.TransplantActivity;

//import com.yuhj.ontheway.R;
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
//import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
//import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

@SuppressLint("ValidFragment")
public class PlantFragment extends Fragment implements OnItemClickListener {
	private View dictView;
	private Context context;
	private ViewPager viewPager;
	private List<View> views;
	private ScheduledExecutorService scheduledExecutorService;
	private int currentItem = 0;

	private ImageView point;
	private int[] images = { R.drawable.slidepictureaa, R.drawable.slidepictureab,
			R.drawable.slidepictureac };
	private int[] points = { R.drawable.point01, R.drawable.point02,
			R.drawable.point03 };
	// private List<Song> list_song = null;
	private Display display;
	// private PullToRefreshScrollView mPullRefreshScrollView;
	private ScrollView mScrollView;
	private final int SET_PAGER_ITEM = 1001;
	private final int TO_UP = 1002;
	private final int SET_CLASS_TRANSLATE = 1003;

	private GridView areaGridView;
	private ArrayList<AreaItem> areaList = new ArrayList<AreaItem>();
	private int[] areaimages = new int[] { R.drawable.ic_cate_bangumi,
			R.drawable.ic_cate_animation, R.drawable.ic_cate_music,
			R.drawable.ic_cate_game };
	private String[] areatexts = new String[] { "烟苗移栽", "长势监测", "施肥建议", "种植技术" };

	public PlantFragment(Context context) {
		this.context = context;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		for (int i = 0; i < areaimages.length; i++) {
			AreaItem item = new AreaItem();
			item.setImg(areaimages[i]);
			item.setText(areatexts[i]);
			areaList.add(item);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		dictView = inflater.inflate(R.layout.activity_plantfragment, container,
				false);
		display = getActivity().getWindowManager().getDefaultDisplay();

		areaGridView = (GridView) dictView.findViewById(R.id.AreaGridView);
		areaGridView.setAdapter(new AreaGridAdapter(getActivity(), areaList));
//		areaGridView.setOnItemClickListener(null);

		initViewPager(inflater);
		// initListView(inflater);
		// initScrollView();
		// myCreate();
		return dictView;
	}

	private void initViewPager(LayoutInflater inflater) {

		viewPager = (ViewPager) dictView.findViewById(R.id.myViewPager);
		point = (ImageView) dictView.findViewById(R.id.point);
		int height = display.getHeight() / 3;
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				LayoutParams.FILL_PARENT, height);
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
		// plant_linear2 = (LinearLayout)
		// dictView.findViewById(R.id.plant_linear2);
		// plant_linear2.setOnClickListener(new MyOnClick(SET_CLASS_TRANSLATE));

		// timer = new Timer();
		// setTimerTask();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		Map<String, Object> map = (Map<String, Object>) parent
				.getItemAtPosition(position);
		String itemName = (String) map.get("areatexts");

		if (itemName == "烟苗移栽") {
			Intent transplantIntent = new Intent(this.context,
					TransplantActivity.class);
			startActivity(transplantIntent);
		}

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

		@Override
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

				mScrollView.fullScroll(View.FOCUS_UP);
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
				intent.setClass(context, TransplantActivity.class);
				startActivity(intent);

				break;

			default:
				break;
			}
		}
	}

	// private class MyOnItemClick implements OnItemClickListener {
	//
	//
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	//
	// // Map<String, Object> map = (Map<String, Object>) parent
	// // .getItemAtPosition(position);
	// // String itemName = (String) map.get("areatexts");
	// //
	// // if (itemName == "移栽")
	// // {
	// // Intent transplantIntent = new Intent(context,
	// TransplantActivity.class);
	// // startActivity(transplantIntent);
	// // }
	//
	//
	//
	//
	//
	// }
	// }


	private void initListView(LayoutInflater inflater) {

		// song_grid = (ListView) dictView.findViewById(R.id.song_grid);
		// Song song = new Song();
		// list_song = song.getSongs();
		// SongGridApapter songGridApapter = new SongGridApapter(inflater,
		// list_song, context);
		// song_grid.setAdapter(songGridApapter);
		// setListViewHeightBasedOnChildren(song_grid);
	}

	private void setListViewHeightBasedOnChildren(ListView song_grid) {

		ListAdapter listAdapter = song_grid.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, song_grid);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		LayoutParams params = song_grid.getLayoutParams();
		params.height = totalHeight
				+ (song_grid.getDividerHeight() * (listAdapter.getCount() - 1));
		song_grid.setLayoutParams(params);
	}

	private void initScrollView() {

		// mPullRefreshScrollView = (PullToRefreshScrollView) dictView
		// .findViewById(R.id.pull_refresh_scrollview);
		// mPullRefreshScrollView
		// .setOnRefreshListener(new OnRefreshListener<ScrollView>() {
		//
		// @Override
		// public void onRefresh(
		// PullToRefreshBase<ScrollView> refreshView) {
		// new GetDataTask().execute();
		// }
		// });
		// mScrollView = mPullRefreshScrollView.getRefreshableView();
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
			// mPullRefreshScrollView.onRefreshComplete();

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
				// String dict_write02 = ClientRequestOperation.encode("吐");
				// String url =
				// "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=q0Hf2pchsKTZtFMsdSolj1MG&q="
				// + dict_write02 + "&from=auto&to=auto";
				// ParseJson parseJson = new ParseJson(
				// ClientRequestOperation.executeHttpGet(url));
				// try {
				// ResultJson result = parseJson.Json();
				//
				// System.out.println("------你懂的----"+result.getList_char().get(0).getSrc());
				// } catch (JSONException e) {
				// // TODO 自动生成的 catch 块
				// e.printStackTrace();
				// }

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
