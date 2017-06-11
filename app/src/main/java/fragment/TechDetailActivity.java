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

public class TechDetailActivity extends Activity {

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


        newsImage1.setBackground(getResources().getDrawable(R.drawable.techpic01));//变形

        mTitle.setText("烟草炭疽病");
        mAuthor.setText("烟草百科");
        mPubDate.setText("2012年04月21日");
        mContent.setText( "        烟草炭疽病由属半知菌亚六包盘孢属的真菌侵染引起的。烟草炭疽病于1922年由巴西首次报道以后，德国、日本、美国、中国、澳大利亚、印度、朝鲜和非洲也相继发现。此病在烟草各生育期皆可发生，但以苗期发生普遍而严重。幼苗叶片病斑密布，严重发病时往往使整片烟苗毁掉，一般发病时虽不至于苗毁，但幼苗生势差，而且移栽大田后仍可继续为害，招致较大损失。"
        +"\n"+"         烟草各生育期均可发生，以苗期为害最重。发病初期，叶产生暗绿色水渍状小点，1-2天可扩展成直径2-5毫米的圆斑。中央为灰白色或黄褐色，稍凹陷，边缘明显，呈赤褐色，稍隆起。天气多雨，叶组织柔嫩，病斑多呈褐色或黄褐色，有时有轮纹或产生小黑点，即病菌的分生孢子盘。天气干燥，病斑多呈黄白色，不出现轮纹和小黑点。重病时，病斑密集合并，使叶片扭缩或枯焦。叶脉及茎部病斑呈梭形，凹陷开裂，黑褐色。重则幼苗枯死。成株期，多先由脚叶发病，逐渐向上蔓延。茎部病斑较大，旦网状纵裂条斑，凹陷，黑褐色，天气潮湿时，病部产生黑色小点 。"
                        +"\n"+"        病菌以菌丝随病株残体在土壤和肥料中越冬，也可以菌丝潜入种子内或以分生孢子粘附在种子表面，成为翌年病害初侵染的菌源。在苗床发病后，移栽大田也发病，多限于底叶，病组织上产生的分生孢子借风雨形成再侵染。该菌温度适应范围广，以25-30℃最适于发病。水分对病菌的繁殖和传播起着关键作用，由雨水或灌溉将粘连于分生孢子盘上的 分生孢子淋溅分散，在叶面具有水膜的情况下萌发侵染。"



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
