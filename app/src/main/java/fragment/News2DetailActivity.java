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

public class News2DetailActivity extends Activity {

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


        newsImage1.setBackground(getResources().getDrawable(R.drawable.newspic02));//变形

        mTitle.setText("通知：云南省烟草病虫害预测预报及综合防治2017年工作要求");
        mAuthor.setText("省局");
        mPubDate.setText("2017年05月25日");
        mContent.setText( "        2017年云南省烟草病虫害预测预报及综合防治工作重点推广以生物防治、物理防治、农业防治为主的绿色防控技术，围绕2017年烟草病虫害预测预报工作，加大烟草病虫害综合防治技术规范、烟草病虫害预测预报技术和工作规范和烟草农药合理使用技术规范的推广力度，切实做到烟草病虫害预测预报为烟叶生产服务。"
        +"\n"+"         在省测报站确定的全省“五病两虫”，即烟草花叶类病毒病（CMV、TMV等）、赤星病、野火病、黑胫病、根结线虫病、烟蚜、棉铃虫\\烟青虫为重点测报对象外，各州市测报站根据本地病虫害发生种类及危害程度，确定2014年本地区重点测报对象，进一步落实病虫害系统观测圃和普查点的工作人员，严格按烟草病虫害预测预报技术和工作规范开展病虫害预测预报。根据各测报站点的功能及职责，细化网站管理制度，量化考核指标，及时将病虫调查监测数据及相关信息传送到上级测报站点。"
                        +"\n"+"        统一测报表格，按时上报测报数据。各州市严格按照省测报站制定的表格格式进行测报和上报。以州市测报站和县测报站为单位自3月～10月每10天（当月10日、20日、30日）调查当地烟草病虫害发生情况，并于12日、22日、2日上报当地烟草病虫害发生危害调查数据（包括系统观测和普查原始数据），邮箱地址：cbzh@yntsti.com或yuqing909@163.com，并在月底发布、上传“烟区病虫情报”。各烟区要及时发布烟草病虫情报旬报和月报指导当地烟区的烟草病虫害综合防治。调查表见2014年云南省烟草病虫害发生流行调查表。"



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
