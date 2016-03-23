package com.shequbao.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;
//全局工具类
public class BaseActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
	}
	public Intent intent = new Intent();

	// 跳转页面函数
	public void open_activity(Class<?> classname) {
		intent.setClass(this, classname);
		startActivity(intent);
	}
	
	public void showtoast(Context context,String content){
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}
}
