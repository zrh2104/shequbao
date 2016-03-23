package com.shequbao.fragment;

import com.shequbao.R;
import com.shequbao.R.drawable;
import com.shequbao.base.MyV4Fragment;
import com.shequbao.tools.MyPreference;
import com.shequbao.useraccount.LoginAct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class fragment_me_page extends MyV4Fragment implements OnClickListener{

	private TextView logintextview;
	private View view;
	private TextView logoff;
	private ImageView loginimage;
	@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
		// 获取fragment_homepage页面的view对象
		view = inflater.inflate(R.layout.fragment_me_page, null);
		logintextview=(TextView) view.findViewById(R.id.me_login_name);
		logoff=(TextView) view.findViewById(R.id.me_back_login);
		loginimage=(ImageView) view.findViewById(R.id.me_login_image);
		logintextview.setOnClickListener(this);
		logoff.setOnClickListener(this);
		GetLoginInfo();
	return view;
	
	
}
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.me_login_name:
open_activity(LoginAct.class);
			break;
		case R.id.me_back_login:
MyPreference.getInstance(getActivity()).SetIsSavePwd(false);
MyPreference.getInstance(getActivity()).SetLoginName("");
MyPreference.getInstance(getActivity()).SetPassword("");
logoff.setVisibility(View.INVISIBLE);
logintextview.setText("未登录");
loginimage.setVisibility(View.INVISIBLE);
logintextview.setClickable(true);

break;

		default:
			break;
		}
	}
private void GetLoginInfo() {
	boolean issavepwd = MyPreference.getInstance(getActivity()).IsSavePwd();
	if(issavepwd){
		logintextview.setText(MyPreference.getInstance(getActivity()).getLoginName());
	logoff.setVisibility(View.VISIBLE);
	logintextview.setClickable(false);
	loginimage.setVisibility(View.VISIBLE);
	loginimage.setImageResource(R.drawable.touxiang);
	}
	
}
}
