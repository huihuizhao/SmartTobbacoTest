package com.esri.arcgisruntime.sample.smarttobacco;

import android.content.Context;
import android.widget.Toast;

public class MyToast {
	public static void showToast(Context context, String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}