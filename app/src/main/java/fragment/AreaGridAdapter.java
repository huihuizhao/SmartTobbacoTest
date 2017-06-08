package fragment;

import java.util.List;

//import com.yuhj.ontheway.R;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.esri.arcgisruntime.sample.smarttobacco.R;

public class AreaGridAdapter  extends BaseAdapter{
	private Context mContext;
	private List<AreaItem> mList;
	public AreaGridAdapter(Context mContext,List<AreaItem> mList){
		this.mContext = mContext;
		this.mList = mList;	
	}
	

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public AreaItem getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		AreaItem areaItem = getItem(position);
		convertView = LayoutInflater.from(mContext).inflate(R.layout.area_item, null);
		ImageView areaImageView = (ImageView) convertView.findViewById(R.id.areaImageView);
		TextView areaTextView = (TextView) convertView.findViewById(R.id.areaTextView);
		areaImageView.setImageResource(areaItem.getImg());
		areaTextView.setText(areaItem.getText());
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 处理跳转逻辑
				Intent i = new Intent();
				i.putExtra("AreaType",position+1);
//				if(position == 7){
//					i.setClass(mContext, TVActivity.class);
//				}else{
//					i.setClass(mContext, DonghuaActivity.class);
//				}
				mContext.startActivity(i);
				// 动画过渡
//				((Activity) mContext).overridePendingTransition(R.anim.push_left_in,
//						R.anim.push_no);
			}
		});
		
		
		return convertView;
	}
}
