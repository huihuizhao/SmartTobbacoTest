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
import android.widget.ImageView;
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


        newsImage1.setBackground(getResources().getDrawable(R.drawable.newspic01));//变形

        mTitle.setText("新闻：云南省烟草专卖局（公司）局长（经理）陈卫东调研大理两烟工作(图)");
        mAuthor.setText("云南新闻");
        mPubDate.setText("2017年05月25日");
        mContent.setText( "        5月23日至24日，云南省烟草专卖局（公司）局长陈卫东到大理州调研两烟工作，云南省烟草公司办公室、烟叶处、卷烟销售处负责人，大理州政府副州长段玠，大理州烟草公司经理樊在斗，红塔集团大理卷烟厂厂长袁国旺等陪同调研。"
        +"\n"+"         通过实地调研并听取工商双方工作情况汇报后，陈卫东充分肯定大理州“两烟”工作。陈卫东指出，大理州经济运行保持良好发展势头难能可贵，有力地支撑了大理州“两烟”的发展。青海湖水源工程建设堪称“以工哺农、产业富农”的典范；现代物流建设体现了精益管理、精准控制、持续改善、模式创新，体系化的贝壳管理模式堪称典范；下庄烟站“两烟”业务、专销业务聚合，人员精减高效，管理精细、流程清晰、制度完善、管控到位，堪称基层站所建设管理的典范；促农增收工作形成生产链，实现工场化，堪称典范；红塔集团大理卷烟厂通过就地技术改造后实现了集约发展、清洁发展、绿色发展，堪称现代工厂的典范。工商双方的这些经验和模式很实用、可复制、可推广，为全省烟草转型发展探索了新路子。"
                        +"\n"+"        就下一步大理烟草工作，陈卫东强调，一是要切实保持良好发展势头，促进“两烟”协调发展。要正确处理好烟叶生产和卷烟销售的关系，以税利增长为核心，既要保持烟叶的优势，又要培育卷烟的后发优势。要正确处理好工业和商业的关系，推动工商深度合作。二是切实转变营销观念，实现卷烟促量增效。要树立品牌培育新理念，挖掘旅游资源优势，转变营销管理方式，改善提升营销平台，推动营销队伍转型，加大品牌培育，加大营销市场化改革。三是切实转变烟叶生产方式，促进烟叶提质增效。进一步深化对大理烟叶优势的认识，在提升标准化生产水平上下功夫，改进烟叶生产管理方式，加大创新成果转化集成推广。四是强化专卖内管，促进市场健康发展。内管和外打相结合，抓好大要案查处，加强市场日常监管，坚决管好边界，规范投放行为，加强专卖队伍建设，严格纪律、严格内部管理，加强烟叶生产过程监管。五是持续开展模式创新、机制创新、管理创新、技术创新等，促进企业转型升级。六是要抓好企业党的建设，激发内生动力。抓好领导班子、领导干部，抓机构、抓平台、抓基层组织、抓党员队伍，为企业发展营造风清气正的政治生态。"



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
