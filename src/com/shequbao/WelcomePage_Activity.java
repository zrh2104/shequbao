package com.shequbao;

import java.util.Timer;
import java.util.TimerTask;

import com.shequbao.base.BaseActivity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomePage_Activity extends BaseActivity {
	public Intent intent = new Intent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcomepage);
		Timer time = new Timer();
		TimerTask timetask = new TimerTask() {

			@Override
			public void run() {
				open_activity(HomePage_Activity.class);
				finish();
			}
		};
		// 延时３秒跳转
		time.schedule(timetask, 1000);

	}

}
