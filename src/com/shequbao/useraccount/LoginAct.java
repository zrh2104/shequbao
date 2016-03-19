package com.shequbao.useraccount;

import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shequbao.HomePage_Activity;
import com.shequbao.R;
import com.shequbao.base.BaseActivity;
import com.shequbao.fragment.fragment_me_page;
import com.shequbao.tools.MyPreference;
import com.shequbao.widget.ClearEditTextView;

public class LoginAct extends BaseActivity {
	private ClearEditTextView name;
	private ClearEditTextView pwd;
	private TextView login;
	private TextView register;
	private String postStr = "";
	private Intent intent;
	private RequestQueue mqueue;
	private String username;
	private String password;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		name = (ClearEditTextView) findViewById(R.id.login_name);
		pwd = (ClearEditTextView) findViewById(R.id.login_password);
		login = (TextView) findViewById(R.id.login_submit);
		register = (TextView) findViewById(R.id.register_submit);
		intent = new Intent(LoginAct.this, RegisterAct.class);
		mqueue = Volley.newRequestQueue(this);
		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (LoginAct.this.loginIsSuccsee()) {
					// 用户输入正确
					login();
				}
			}
		});
		register.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// name.setText("");
				// pwd.setText("");
				open_activity(RegisterAct.class);
			}
		});

	}

	/*
	 * 
	 * 取得用户信息处理登录
	 */

	public void doLogin() {
		//记录用户名和密码到内存上
		saveloginname();
		// 传递到下个Activity
		Bundle bundle = new Bundle();
		bundle.putBoolean("boolean", true);
		Intent intent = new Intent(LoginAct.this,HomePage_Activity.class);
		// this.setResult(0, this.getIntent().putExtras(bundle));
		intent.putExtras(bundle);
		startActivity(intent);
		this.finish();

	}

	/*
	 * 判断用户输入是否规范 录入信息验证 验证是否通过
	 */

	private boolean loginIsSuccsee() {
		// 获取用户输入的用户名,密码
		username = name.getText().toString();
		password = pwd.getText().toString();
		if ("".equals(username)) {
			// 用户输入用户名为空
			Toast.makeText(LoginAct.this, "用户名不能为空!", Toast.LENGTH_SHORT)
					.show();
			return false;
		} else if ("".equals(password)) {
			// 密码不能为空
			Toast.makeText(LoginAct.this, "密码不能为空!", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private void login() {
		postStr =getString(R.string.host_address)+"/xgcsq/login";
		StringRequest stringRequest = new StringRequest(Method.POST, postStr,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {

						System.out.println("返回的数据是：" + response);
						corretaccount(response);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

					}
				}) {
			//重写getParams()方法，用户提交post数据
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("username", username);
				map.put("password", password);
				return map;
			}
		};
		mqueue.add(stringRequest);
	}

	// 判断服务器查询的结果是否帐号和密码正确
	private void corretaccount(String response) {
		if ("OK".equals(response)) {
			System.out.println("登录成功");
			showtoast(LoginAct.this, "登录成功");
			doLogin();

		} else if ("NO".equals(response)) {
			AlertDialog.Builder builder = new Builder(LoginAct.this);
			builder.setMessage("用户名或密码输入错误！请重新登录");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).show();
		}
	}
	//正确登录后调用，记录登录名和密码
private void saveloginname(){
	MyPreference.getInstance(LoginAct.this).SetIsSavePwd(true);

	MyPreference.getInstance(LoginAct.this).SetLoginName(username);
	MyPreference.getInstance(LoginAct.this).SetPassword(password);

}
}