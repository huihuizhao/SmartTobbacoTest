package com.esri.arcgisruntime.sample.smarttobacco;

import android.app.Application;
import com.baidu.location.LocationClient;

public class MyApplication extends Application {
	public LocationClient locationClient;
	//public TextView AppResult;
	@Override
	public void onCreate() {
		super.onCreate();
		locationClient=new LocationClient(this);
		
	}  
}