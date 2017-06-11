package fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.esri.arcgisruntime.sample.smarttobacco.R;

import java.util.HashMap;

public class News3DetailActivity extends Activity {

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
    private ImageView newsImage1;


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
        newsImage1 = (ImageView) findViewById(R.id.newsImage1);
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


        newsImage1.setBackground(getResources().getDrawable(R.drawable.newspic03));//变形

        mTitle.setText("动态：云南烤烟移栽圆满结束 移栽面积609万亩");
        mAuthor.setText("云南网");
        mPubDate.setText("2017年05月17日");
        mContent.setText( "        截至5月15日，全省烤烟移栽工作圆满结束，移栽面积609万亩，推广膜下小苗节水移栽433万亩，全面实现在最佳节令移栽目标，为全省烤烟生产提质增效和促农增收打下坚实基础。"
        +"\n"+"         今年以来，全省烟区各级党委、政府和烟草部门加大烟叶供给侧结构性改革力度，着力在“控总量、转方式、促增收”上下功夫，紧紧围绕最佳节令移栽中心任务，扎实有序推进烤烟移栽工作。坚持市场导向，以“2260”高端特色烟叶开发为抓手，加大用好田好地栽烟宣传力度，优化种植布局，巩固云南烟叶优势地位。培育职业烟农，全省培训职业烟农累计7.5万户，种烟农户持续优化，全省户均种烟8.38亩，同比增加0.88亩，生产组织化水平持续提升。抢抓移栽节令，围绕烤烟移栽最佳节令，倒排育苗播种时间，加强烟苗管理，合理安排预整地进度，严格整地质量标准，确保全省在最佳节令高标准高质量完成移栽。推广先进技术，新增有机肥施用补贴每亩15元，全省烤烟推广有机肥施用面积525万亩，推广节水滴灌20万亩、膜下小苗节水移栽433万亩，为最佳节令完成烤烟移栽提供了有力保障。突出集中移栽，严格落实同一连片3至5天内集中移栽要求，缩短移栽周期，保证苗齐苗壮和大田烟株整齐度，提高生产质量均衡性。"
                        +"\n"+"        "



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
