package fragment;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.esri.arcgisruntime.sample.smarttobacco.R;

public class NewsDetailActivity  extends Activity {

    private final static int DATA_LOAD_ING = 0x001;
    private final static int DATA_LOAD_COMPLETE = 0x002;

    private boolean isRefresh = false;

    private RelativeLayout mHeader;
    private ImageButton mBack;
    private TextView mHeadTitle;
    private ProgressBar mProgressbar;
    private ImageButton mRefresh;

    private ScrollView mScrollView;
    private TextView mTitle;
    private TextView mAuthor;
    private TextView mPubDate;
    private TextView mContent;

    private WebView mWebView;

    private GestureDetector gd;
    private boolean isFullScreen;


    private String newsDetail_url;
    private int newsType;
    private long _uid;
//	private AppContext appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);


        this.initView();
        this.initData();


    }


    /**

     */
    private void initView() {
//        mHeader = (RelativeLayout) findViewById(R.id.news_detail_header);
//        mBack = (ImageButton) findViewById(R.id.main_head_back_button);
//        mBack.setOnClickListener(new NewsDetailOnClickListener());
//        mHeadTitle = (TextView) findViewById(R.id.systv);
//        mHeadTitle.setText(R.string.news_dedail_title);
//        mProgressbar = (ProgressBar) findViewById(R.id.main_head_progress);
//        mRefresh = (ImageButton) findViewById(R.id.main_head_refresh_button);
//        mRefresh.setOnClickListener(new NewsDetailOnClickListener());

        mScrollView = (ScrollView) findViewById(R.id.news_detail_scrollview);
        mTitle = (TextView) findViewById(R.id.news_detail_title);
        mAuthor = (TextView) findViewById(R.id.news_detail_author);
        mPubDate = (TextView) findViewById(R.id.news_detail_date);
        mContent = (TextView) findViewById(R.id.news_detail_content);

//        mWebView = (WebView) findViewById(R.id.news_detail_webview);
//        mWebView.getSettings().setJavaScriptEnabled(false);
//        mWebView.getSettings().setSupportZoom(true);
//        mWebView.getSettings().setBuiltInZoomControls(true);
//        mWebView.getSettings().setDefaultFontSize(15);



    }


    private void initData() {
        loadNewsDetailData(newsDetail_url, false);

    }



    private void loadNewsDetailData(final String newsDetail_url, boolean isRefresh) {
        HashMap params = new HashMap();
        params.put("newsType", newsType);
        params.put("newsDetail_url", newsDetail_url);
        params.put("isRefresh", isRefresh);


        mTitle.setText("新闻：云南省烟草专卖局（公司）局长（经理）陈卫东调研大理两烟工作(图)");
        mAuthor.setText("云南新闻");
        mPubDate.setText("2017年05月25日");
        mContent.setText( "        5月23日至24日，云南省烟草专卖局（公司）局长陈卫东到大理州调研两烟工作，云南省烟草公司办公室、烟叶处、卷烟销售处负责人，大理州政府副州长段玠，大理州烟草公司经理樊在斗，红塔集团大理卷烟厂厂长袁国旺等陪同调研。"
        +"\n"+"        5月23日至24日，云南省烟草专卖局（公司）局长陈卫东到大理州调研两烟工作，云南省烟草公司办公室、烟叶处、卷烟销售处负责人，大理州政府副州长段玠，大理州烟草公司经理樊在斗，红塔集团大理卷烟厂厂长袁国旺等陪同调研。"
                        +"\n"+"        5月23日至24日，云南省烟草专卖局（公司）局长陈卫东到大理州调研两烟工作，云南省烟草公司办公室、烟叶处、卷烟销售处负责人，大理州政府副州长段玠，大理州烟草公司经理樊在斗，红塔集团大理卷烟厂厂长袁国旺等陪同调研。"
                        +"\n"+"        5月23日至24日，云南省烟草专卖局（公司）局长陈卫东到大理州调研两烟工作，云南省烟草公司办公室、烟叶处、卷烟销售处负责人，大理州政府副州长段玠，大理州烟草公司经理樊在斗，红塔集团大理卷烟厂厂长袁国旺等陪同调研。"
                        +"\n"+"        5月23日至24日，云南省烟草专卖局（公司）局长陈卫东到大理州调研两烟工作，云南省烟草公司办公室、烟叶处、卷烟销售处负责人，大理州政府副州长段玠，大理州烟草公司经理樊在斗，红塔集团大理卷烟厂厂长袁国旺等陪同调研。"
                        +"\n"+"        5月23日至24日，云南省烟草专卖局（公司）局长陈卫东到大理州调研两烟工作，云南省烟草公司办公室、烟叶处、卷烟销售处负责人，大理州政府副州长段玠，大理州烟草公司经理樊在斗，红塔集团大理卷烟厂厂长袁国旺等陪同调研。"
                        +"\n"+"        5月23日至24日，云南省烟草专卖局（公司）局长陈卫东到大理州调研两烟工作，云南省烟草公司办公室、烟叶处、卷烟销售处负责人，大理州政府副州长段玠，大理州烟草公司经理樊在斗，红塔集团大理卷烟厂厂长袁国旺等陪同调研。"
                        +"\n"+"        5月23日至24日，云南省烟草专卖局（公司）局长陈卫东到大理州调研两烟工作，云南省烟草公司办公室、烟叶处、卷烟销售处负责人，大理州政府副州长段玠，大理州烟草公司经理樊在斗，红塔集团大理卷烟厂厂长袁国旺等陪同调研。"
                        +"\n"+"        5月23日至24日，云南省烟草专卖局（公司）局长陈卫东到大理州调研两烟工作，云南省烟草公司办公室、烟叶处、卷烟销售处负责人，大理州政府副州长段玠，大理州烟草公司经理樊在斗，红塔集团大理卷烟厂厂长袁国旺等陪同调研。"
                        +"\n"+"        5月23日至24日，云南省烟草专卖局（公司）局长陈卫东到大理州调研两烟工作，云南省烟草公司办公室、烟叶处、卷烟销售处负责人，大理州政府副州长段玠，大理州烟草公司经理樊在斗，红塔集团大理卷烟厂厂长袁国旺等陪同调研。"


        );




    }





    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        gd.onTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.news_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);
    }


}
