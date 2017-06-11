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

public class TechDetail2Activity extends Activity {

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


        newsImage1.setBackground(getResources().getDrawable(R.drawable.techpic02));//变形

        mTitle.setText("烟草天蛾");
        mAuthor.setText("烟草百科");
        mPubDate.setText("2012年04月21日");
        mContent.setText( "        烟草天蛾（学名：Manduca sexta），又名传粉夜蛾，是天蛾科的一种蛾，在美洲大陆大部分地区可见。它很容易与番茄天蛾（Manduca quinquemaculata）混淆。两者长相相似，且属于同一属。其幼虫都以茄科植物的叶子为食。烟草天蛾两侧有7对斜线，番茄天蛾则有8个V形图案。烟草天蛾身体有一种机制，选择性地吸收和分泌烟草中的神经毒素尼古丁。"
        +"\n"+"         烟草天蛾是一种常见的模式生物，特别在神经生物学，这是由于它神经系统很容易获得并且其生命周期较短。它是用于各种生物医学和生物科学实验。它可以很容易用小麦胚芽为主的食物饲养。幼虫比较大，因而比较容易解剖和分离器官。"
                        +"\n"+"        烟草天蛾，幼虫身体的每一侧均布满斜纹，这种昆虫在美国最为常见，由于喜欢吃很多种蔬菜作物的叶子和茎，它们被视为一种害虫。这只独特的天蛾幼虫被众多白色棉花状昆虫覆盖。它们是绒茧蜂，幼年时将天蛾幼虫的身体当成自己的家。白色的好似米粒一样的东西实际上是绒茧蜂的卵。"



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
