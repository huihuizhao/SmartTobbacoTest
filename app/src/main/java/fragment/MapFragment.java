package fragment;

import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.data.ArcGISFeature;
import com.esri.arcgisruntime.data.Feature;
import com.esri.arcgisruntime.data.FeatureQueryResult;
import com.esri.arcgisruntime.data.QueryParameters;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.layers.ArcGISMapImageLayer;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.layers.SublayerList;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.GeoElement;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.IdentifyLayerResult;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.sample.smarttobacco.AddActivity;
import com.esri.arcgisruntime.sample.smarttobacco.DisasterActivity;
import com.esri.arcgisruntime.sample.smarttobacco.MainActivity;
import com.esri.arcgisruntime.sample.smarttobacco.R;
import com.esri.arcgisruntime.sample.smarttobacco.TransplantActivity;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import android.view.KeyEvent;
import android.widget.Toast;

import static android.app.AlertDialog.*;

//import com.yuhj.ontheway.R;


public class MapFragment extends Fragment implements OnItemClickListener {
    private View dictView;
    private Context context;
    private ViewPager viewPager;
    private List<View> views;
    private ScheduledExecutorService scheduledExecutorService;
    private int currentItem = 0;

    private ImageView point;
    //	private int[] images = { R.drawable.zaihai01, R.drawable.zaihai02,
//			R.drawable.zaihai03 };
//	private int[] points = { R.drawable.point01, R.drawable.point02,
//			R.drawable.point03 };
    // private List<Song> list_song = null;
    private Display display;
    // private PullToRefreshScrollView mPullRefreshScrollView;
    private ScrollView mScrollView;
    private final int SET_PAGER_ITEM = 1001;
    private final int TO_UP = 1002;
    private final int SET_CLASS_TRANSLATE = 1003;

    private GridView disasterGridView;
    private ArrayList<AreaItem> disasterList = new ArrayList<AreaItem>();
    private int[] disasterimages = new int[]{R.drawable.ic_cate_bangumi,
            R.drawable.ic_cate_animation, R.drawable.ic_cate_music,
            R.drawable.ic_cate_game};
    private String[] areatexts = new String[]{"虫害", "地图", "冰雹地图", "洪涝监测"};


    //	private ServiceFeatureTable mServiceFeatureTable;
    private MapView mMapView;
    private Callout mCallout;
    private FeatureLayer mFeatureLayer;
    private ArcGISFeature mSelectedArcGISFeature;
    private android.graphics.Point mClickPoint;
    private ServiceFeatureTable mServiceFeatureTable;

    private ServiceFeatureTable mServiceFeatureTableTown;
    private  FeatureLayer mFeaturelayerTown;

    private Snackbar mSnackbarSuccess;
    private Snackbar mSnackbarFailure;
    private String mSelectedArcGISFeatureAttributeValue;
    private String mSelectedArcGISFeatureAttributeValue2;
    private boolean mFeatureUpdated;
    private View mCoordinatorLayout;
    private ProgressDialog mProgressDialog;

    private ImageButton btnTransplant, btnDisaster, btnLayer;

    private ArcGISMapImageLayer mMapImageLayer;
    private SublayerList mLayers;

    // The layer on/off menu items.
    private MenuItem mCities = null;
    private MenuItem mContinent = null;
    private MenuItem mWorld = null;

    private SearchView mSearchView;

    public MapFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

//		for (int i = 0; i < disasterimages.length; i++) {
//			AreaItem item = new AreaItem();
//			item.setImg(disasterimages[i]);
//			item.setText(areatexts[i]);
//			disasterList.add(item);
//		}


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dictView = inflater.inflate(R.layout.fragment_map, container,
                false);
//		dictView = inflater.inflate(R.layout.activity_main, container,
//				false);

        display = getActivity().getWindowManager().getDefaultDisplay();
        setHasOptionsMenu(true);//需要添加这行代码

        initViewPager(inflater);
        // initListView(inflater);
        // initScrollView();
        // myCreate();

        // inflate MapView from layout
        mSearchView = (SearchView) dictView.findViewById(R.id.searchView1);

        mCoordinatorLayout = dictView.findViewById(R.id.snackbarPosition);

        // inflate MapView from layout
        mMapView = (MapView) dictView.findViewById(R.id.mapView);

        // create a map with the streets basemap
//    final ArcGISMap map = new ArcGISMap(Basemap.createStreets());
        final ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, 24.570, 103.268, 14);
        //set an initial viewpoint  , , , 14
//    map.setInitialViewpoint(new Viewpoint(new Point(-100.343, 34.585, SpatialReferences.getWgs84()), 1E8));
//    map.setInitialViewpoint(new Viewpoint(new Point(103.226, 24.570, SpatialReferences.getWgs84()), 1E8));

        // set the map to be displayed in the mapview
        mMapView.setMap(map);

        // get callout, set content and show
        mCallout = mMapView.getCallout();

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle(getResources().getString(R.string.progress_title));
        mProgressDialog.setMessage(getResources().getString(R.string.progress_message));

        // create feature layer with its service feature table
        // create the service feature table
        mServiceFeatureTable = new ServiceFeatureTable(getResources().getString(R.string.sample_service_url));
        // create the feature layer using the service feature table
        mFeatureLayer = new FeatureLayer(mServiceFeatureTable);

        // set the color that is applied to a selected feature.
        mFeatureLayer.setSelectionColor(Color.rgb(0, 255, 255)); //cyan, fully opaque
        // set the width of selection color
        mFeatureLayer.setSelectionWidth(3);

        // add the layer to the map
        map.getOperationalLayers().add(mFeatureLayer);


        mServiceFeatureTableTown = new ServiceFeatureTable(getResources().getString(R.string.search_service_url));
        // create the feature layer using the service feature table
        mFeaturelayerTown = new FeatureLayer(mServiceFeatureTableTown);
        mFeaturelayerTown.setOpacity(0.8f);
        //override the renderer
        SimpleLineSymbol lineSymbol= new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLACK, 1);
        SimpleFillSymbol fillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.YELLOW, lineSymbol);
        mFeaturelayerTown.setRenderer(new SimpleRenderer(fillSymbol));

        // add the layer to the map
        map.getOperationalLayers().add(mFeaturelayerTown);




        // set an on touch listener to listen for click events
        mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(getActivity(), mMapView) {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {

                // get the point that was clicked and convert it to a point in map coordinates
                mClickPoint = new android.graphics.Point((int) e.getX(), (int) e.getY());

                // clear any previous selection
                mFeatureLayer.clearSelection();
                mSelectedArcGISFeature = null;

                // identify the GeoElements in the given layer
                final ListenableFuture<IdentifyLayerResult> identifyFuture = mMapView.identifyLayerAsync(mFeatureLayer, mClickPoint, 5, false, 1);

                // add done loading listener to fire when the selection returns
                identifyFuture.addDoneListener(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // call get on the future to get the result
                            IdentifyLayerResult layerResult = identifyFuture.get();
                            List<GeoElement> resultGeoElements = layerResult.getElements();

                            if (resultGeoElements.size() > 0) {
                                if (resultGeoElements.get(0) instanceof ArcGISFeature) {
                                    mSelectedArcGISFeature = (ArcGISFeature) resultGeoElements.get(0);
                                    // highlight the selected feature
                                    mFeatureLayer.selectFeature(mSelectedArcGISFeature);
                                    // show callout with the value for the attribute "typdamage" of the selected feature
//                  mSelectedArcGISFeatureAttributeValue = (String) mSelectedArcGISFeature.getAttributes().get("typdamage");
                                    String town = (String) mSelectedArcGISFeature.getAttributes().get("TOWN");
                                    String area = mSelectedArcGISFeature.getAttributes().get("FAREA").toString();
                                    String farmer = mSelectedArcGISFeature.getAttributes().get("FARMER").toString();
//                                    String ValueAll = "乡镇：" + mSelectedArcGISFeatureAttributeValue + " 面积：" + Value2 + "亩";
                                    showCallout("农户：" + farmer + "\n" + "面积：" + area + "亩" + "\n" + "乡镇：" + town);
//                                    showCallout("面积：" + area + "亩"+"\n"+"乡镇：" +  town );
//                                    showCallout(ValueAll);
//                  Toast.makeText(getApplicationContext(), "Tap on the info button to change attribute value", Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(getApplicationContext(), "乡镇：" + mSelectedArcGISFeatureAttributeValue+"\n" + "面积：" + Value2 + "亩", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // none of the features on the map were selected
                                mCallout.dismiss();
                            }
                        } catch (Exception e) {
                            Log.e(getResources().getString(R.string.app_name), "Select feature failed: " + e.getMessage());
                        }
                    }
                });
                return super.onSingleTapConfirmed(e);
            }
        });


        mSnackbarSuccess = Snackbar
                .make(mCoordinatorLayout, "Feature successfully updated", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        String snackBarText = updateAttributes(mSelectedArcGISFeatureAttributeValue) ? "Feature is restored!" : "Feature restore failed!";
//                        Snackbar snackbar1 = Snackbar.make(mCoordinatorLayout, snackBarText, Snackbar.LENGTH_SHORT);
//                        snackbar1.show();
                    }
                });

        mSnackbarFailure = Snackbar
                .make(mCoordinatorLayout, "Feature update failed", Snackbar.LENGTH_LONG);


        btnTransplant = (ImageButton) dictView.findViewById(R.id.imageButtonTransplant);
        btnDisaster = (ImageButton) dictView.findViewById(R.id.imageButtonDisaster);
        btnLayer = (ImageButton) dictView.findViewById(R.id.imageButtonLayers);

        btnTransplant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent transplantIntent = new Intent(getActivity(), TransplantActivity.class);
                startActivity(transplantIntent);
            }
        });
        btnDisaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent transplantIntent = new Intent(getActivity(), DisasterActivity.class);
                startActivity(transplantIntent);
            }
        });
        btnLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                mProgressDialog.openOptionsMenu();
//                simulateKey(KeyEvent.KEYCODE_MENU);

                Builder builder = new Builder(getActivity());
                builder.setIcon(R.drawable.layerab);
                builder.setTitle("图层选择");
                final String[] layers = {"弥勒2016年烟田", "耕地", "土壤类型", "乡镇"};
                //    设置一个单项选择下拉框
                /**
                 * 第一个参数指定我们要显示的一组下拉多选框的数据集合
                 * 第二个参数代表哪几个选项被选择，如果是null，则表示一个都不选择，如果希望指定哪一个多选选项框被选择，
                 * 需要传递一个boolean[]数组进去，其长度要和第一个参数的长度相同，例如 {true, false, false, true};
                 * 第三个参数给每一个多选项绑定一个监听器
                 */
                builder.setMultiChoiceItems(layers, null, new DialogInterface.OnMultiChoiceClickListener() {
                    StringBuffer sb = new StringBuffer(100);

                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            sb.append(layers[which] + ", ");
                        }

                        if (which == 0) {
                            if (mLayers.get(0).isVisible()) {
                                // cities layer is on and menu item checked
                                mLayers.get(0).setVisible(false);

                            } else if (!mLayers.get(0).isVisible()) {
                                // cities layer is off and menu item unchecked
                                mLayers.get(0).setVisible(true);

                            }
                        } else if (which == 1) {
                            if (mLayers.get(1).isVisible()) {
                                // continent layer is on and menu item checked
                                mLayers.get(1).setVisible(false);

                            } else if (!mLayers.get(1).isVisible()) {
                                // continent layer is off and menu item unchecked
                                mLayers.get(1).setVisible(true);

                            }

                        } else if (which == 2) {
                            if (mLayers.get(2).isVisible()) {
                                // world layer is on and menu item checked
                                mLayers.get(2).setVisible(false);

                            } else if (!mLayers.get(2).isVisible()) {
                                // world layer is off and menu item unchecked
                                mLayers.get(2).setVisible(true);

                            }

                        } else if (which == 3) {
                            if (mLayers.get(3).isVisible()) {
                                // world layer is on and menu item checked
                                mLayers.get(3).setVisible(false);

                            } else if (!mLayers.get(3).isVisible()) {
                                // world layer is off and menu item unchecked
                                mLayers.get(3).setVisible(true);

                            }

                        }
                        Toast.makeText(getActivity(), "所选图层为：" + sb.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


            }
        });


        mMapImageLayer = new ArcGISMapImageLayer(getResources().getString(R.string.mapservice));
        mMapImageLayer.setOpacity(0.75f);
        // add world cities layers as map operational layer
        map.getOperationalLayers().add(mMapImageLayer);
        // set the map to be displayed in this view
        mMapView.setMap(map);
        // get the layers from the map image layer
        mLayers = mMapImageLayer.getSublayers();




        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                int searchEditTextId =  getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
                EditText textView = (EditText ) dictView.findViewById(searchEditTextId);
//                textView.setTextSize(36);
                String searchContent=textView.getText().toString();
//                textView.setHint("按姓名和标题搜索");
                searchForState(searchContent);
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
//                if (!TextUtils.isEmpty(newText)){
//                    mListView.setFilterText(newText);
//                }else{
//                    mListView.clearTextFilter();
//                }
                return false;
            }
        });




        return dictView;
    }

    /**
     * Handle the search intent from the search widget
     */

    public void searchForState(final String searchString) {

        // clear any previous selections
        mFeaturelayerTown.clearSelection();

        // create objects required to do a selection with a query
        QueryParameters query = new QueryParameters();
        //make search case insensitive
//        query.setWhereClause("upper(STATE_NAME) LIKE '%" + searchString.toUpperCase() + "%'");
        query.setWhereClause("upper(NAME) LIKE '%" + searchString.toUpperCase() + "%'");

        // call select features
        final ListenableFuture<FeatureQueryResult> future = mServiceFeatureTableTown.queryFeaturesAsync(query);
        // add done loading listener to fire when the selection returns
        future.addDoneListener(new Runnable() {
            @Override
            public void run() {
                try {
                    // call get on the future to get the result
                    FeatureQueryResult result = future.get();

                    // check there are some results
                    if (result.iterator().hasNext()) {

                        // get the extend of the first feature in the result to zoom to
                        Feature feature = result.iterator().next();
                        Envelope envelope = feature.getGeometry().getExtent();
                        mMapView.setViewpointGeometryAsync(envelope, 200);

                        //Select the feature
                        mFeaturelayerTown.selectFeature(feature);

                    } else {
                        Toast.makeText(getActivity(), "No states found with name: " + searchString, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Feature search failed for: " + searchString + ". Error=" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e(getResources().getString(R.string.app_name), "Feature search failed for: " + searchString + ". Error=" + e.getMessage());
                }
            }
        });
    }





    public static void simulateKey(final int KeyCode) {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(KeyCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void showCallout(String title) {

        // create a text view for the callout
        RelativeLayout calloutLayout = new RelativeLayout(getContext());

        TextView calloutContent = new TextView(getContext());
        calloutContent.setId(R.id.textview);
        calloutContent.setTextColor(Color.BLACK);
        calloutContent.setTextSize(18);
        calloutContent.setPadding(0, 10, 10, 0);

        calloutContent.setText(title);

        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeParams.addRule(RelativeLayout.RIGHT_OF, calloutContent.getId());

        // create image view for the callout
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_info_outline_black_18dp));
        imageView.setLayoutParams(relativeParams);
        imageView.setOnClickListener(new ImageViewOnclickListener());

        calloutLayout.addView(calloutContent);
//        calloutLayout.addView(imageView);

        mCallout.setLocation(mMapView.screenToLocation(mClickPoint));
        mCallout.setContent(calloutLayout);
        mCallout.show();
    }


    private void initViewPager(LayoutInflater inflater) {

//		viewPager = (ViewPager) dictView.findViewById(R.id.myViewPager);
        point = (ImageView) dictView.findViewById(R.id.point);
        int height = display.getHeight() / 3;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                LayoutParams.FILL_PARENT, height);
//		viewPager.setLayoutParams(params);
        views = new ArrayList<View>();
//		for (int i = 0; i < images.length; i++) {
//
//			ImageView imageView = new ImageView(context);
////			imageView.setImageResource(images[i]);
//			imageView.setScaleType(ScaleType.CENTER_CROP);
//			views.add(imageView);
//		}

//		viewPager.setAdapter(new MyPagerAdapter());
//		viewPager.setOnPageChangeListener(new MyPageChangeListener());
        // plant_linear2 = (LinearLayout)
        // dictView.findViewById(R.id.plant_linear2);
        // plant_linear2.setOnClickListener(new MyOnClick(SET_CLASS_TRANSLATE));

        // timer = new Timer();
        // setTimerTask();


    }


    @Override
//    public void onClick(AdapterView<?> parent, View view, int position,
//                        long id)
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        //此处代码无效 ，按钮单击事件 btnTransplant.setOnClickListener
//        if (view == btnTransplant) {
////            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
////            startActivityForResult(intent, 1001);
////            Intent transplantIntent = new Intent(getActivity(), TransplantActivity.class);
////            startActivity(transplantIntent);
//            Intent disasterIntent = new Intent(getActivity(), DisasterActivity.class);
//            startActivity(disasterIntent);
//        } else if (view == btnDisaster) {
////			Intent intent = new Intent();
////			intent.setType("image/*");
////			intent.setAction(Intent.ACTION_GET_CONTENT);
////			this.startActivityForResult(intent, 1002);
//
//            Intent disasterIntent = new Intent(getActivity(), DisasterActivity.class);
//            startActivity(disasterIntent);
//        } else if (view == btnLayer) {
//            Intent intent = new Intent(getActivity(), MainActivity.class);
//            intent.putExtra("FragmentType", 3);
//            startActivity(intent);
//        }


    }


    /**
     * Defines the listener for the ImageView clicks
     */
    private class ImageViewOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.e("imageview", "tap");
            Intent myIntent = new Intent(getActivity(), null);
            getActivity().startActivityForResult(myIntent, 100);
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
//			point.setImageResource(points[arg0]);
        }
    }

    @Override
    public void onStart() {
        // TODO 自动生成的方法存根

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示
//		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 3,
//				TimeUnit.SECONDS);
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


    @Override
    public void onStop() {
        // TODO 自动生成的方法存根

        scheduledExecutorService.shutdown();
        super.onStop();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Get the sub layer switching menu items.
        mCities = menu.getItem(0);
        mContinent = menu.getItem(1);
        mWorld = menu.getItem(2);

        // set all layers on by default
        mCities.setChecked(true);
        mContinent.setChecked(true);
        mWorld.setChecked(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle menu item selection
        //if-else is used because this sample is used elsewhere as a Library module
        int itemId = item.getItemId();
        if (itemId == R.id.弥勒2016年烟田) {
            if (mLayers.get(0).isVisible() && mCities.isChecked()) {
                // cities layer is on and menu item checked
                mLayers.get(0).setVisible(false);
                mCities.setChecked(false);
            } else if (!mLayers.get(0).isVisible() && !mCities.isChecked()) {
                // cities layer is off and menu item unchecked
                mLayers.get(0).setVisible(true);
                mCities.setChecked(true);
            }
            return true;
        } else if (itemId == R.id.耕地) {
            if (mLayers.get(1).isVisible() && mContinent.isChecked()) {
                // continent layer is on and menu item checked
                mLayers.get(1).setVisible(false);
                mContinent.setChecked(false);
            } else if (!mLayers.get(1).isVisible() && !mContinent.isChecked()) {
                // continent layer is off and menu item unchecked
                mLayers.get(1).setVisible(true);
                mContinent.setChecked(true);
            }
            return true;
        } else if (itemId == R.id.土壤类型) {
            if (mLayers.get(2).isVisible() && mWorld.isChecked()) {
                // world layer is on and menu item checked
                mLayers.get(2).setVisible(false);
                mWorld.setChecked(false);
            } else if (!mLayers.get(2).isVisible() && !mWorld.isChecked()) {
                // world layer is off and menu item unchecked
                mLayers.get(2).setVisible(true);
                mWorld.setChecked(true);
            }
            return true;
        } else if (itemId == R.id.乡镇) {
            if (mLayers.get(3).isVisible() && mWorld.isChecked()) {
                // world layer is on and menu item checked
                mLayers.get(3).setVisible(false);
                mWorld.setChecked(false);
            } else if (!mLayers.get(3).isVisible() && !mWorld.isChecked()) {
                // world layer is off and menu item unchecked
                mLayers.get(3).setVisible(true);
                mWorld.setChecked(true);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


}
