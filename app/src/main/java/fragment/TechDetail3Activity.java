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

public class TechDetail3Activity extends Activity {

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


        newsImage1.setBackground(getResources().getDrawable(R.drawable.techpic03));//变形

        mTitle.setText("烟草天蛾");
        mAuthor.setText("烟草百科");
        mPubDate.setText("2012年04月21日");
        mContent.setText( "        烟草野火病与角斑病是烟草上的重要病害,两种病在病源菌特性、侵染规律以及防治方法等方面都很相似.在田间这两种细菌性病害经常混合发生,在全国各烟区发生较为普遍.野火病在黑龙江、辽宁、山东、云南、四川、陕西等省发生较重,而角斑病在山东、福建、河南、贵州、吉林、云南较重。"
        +"\n"+"         发生特点：野火病主要发生于烟株旺长后期。苗期有时也有发生。叶片受害时，首先产生褐色水渍状小圆斑，周围有一圈很宽的黄晕。病斑扩大后合并为不规则的大斑，上有轮纹。天气潮湿时病部可形成薄层菌，干燥后病斑破裂脱落，叶片毁坏。野火病在云南大田烟株一般在6月下旬至7月上旬开始发病，随后病情迅速上升，至7月中旬出现第1个流行高峰。7月下旬病情下降，8月上旬病情又回升出现第2个高峰期，比第1个高峰期病情增长值低。8月中旬病情下降流行缓慢，直至烟株顶叶成熟。在8月下旬多雨的情况下，田间病情会迅速上升，造成烟株上部叶片受害。"
                        +"\n"+"        防治方法：由于目前尚无理想的抗病优质品种，在防治上，采取“预防为主，综合防治”的方法。实行合理轮作，连作地比轮作地发病重。销毁烟田病残体，减少侵染源，可有效降低危害。适当使用氮肥，注意增施磷钾肥，提高烟株抗病力。及时摘除易感病底脚叶改善田间小气候条件，减少病菌侵染危害。烟草旺长前期及打顶后喷1:1:160的波尔多液或200单位/毫升农用链霉素，7-10天1次，连喷2-3次。"



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
