package com.shequbao.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;
//全局工具类
public class MyV4Fragment extends android.support.v4.app.Fragment {
	public Intent intent = new Intent();

	// 跳转页面函数
	public void open_activity(Class<?> classname) {
		intent.setClass(getActivity(), classname);
		startActivity(intent);
	}
	
	public void showtoast(Context context,String content){
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}
}
