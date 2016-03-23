package com.shequbao.useraccount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import com.shequbao.HomePage_Activity;
import com.shequbao.R;
import com.shequbao.base.BaseActivity;
import com.shequbao.tools.MyPreference;
import com.shequbao.widget.ClearEditTextView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterAct extends BaseActivity {
	private TextView reset;
	private TextView register;
	// 用户名
	private ClearEditTextView uNameEditText;
	// 密码
	private ClearEditTextView pwdEditText1;
	// 重复密码
	private ClearEditTextView pwdEditText2;
	private String userName;
	private String password1;
	private String password2;
	private String postStr;
	private String questStr;
	private String url = "/xgcsq/RegisterServlet";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		url = getString(R.string.host_address) + url;
		reset = (TextView) findViewById(R.id.registor_reset);
		register = (TextView) findViewById(R.id.registor_registor);
		uNameEditText = (ClearEditTextView) findViewById(R.id.registor_username);
		pwdEditText1 = (ClearEditTextView) findViewById(R.id.registor_passwd1);
		pwdEditText2 = (ClearEditTextView) findViewById(R.id.registor_passwd2);
		reset.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				uNameEditText.setText("");
				pwdEditText1.setText("");
				pwdEditText2.setText("");
			}
		});

		register.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (registerIsSuccsee()) {
					register();
				}

			}

		});

	}

	/*
	 * 判断用户注册输入是否规范 录入信息验证 验证是否通过
	 */

	private boolean registerIsSuccsee() {
		// 获取用户输入的信息
		String userName = uNameEditText.getText().toString();
		String password1 = pwdEditText1.getText().toString();
		String password2 = pwdEditText2.getText().toString();

		if ("".equals(userName)) {
			// 用户输入用户名为空
			Toast.makeText(RegisterAct.this, "用户名不能为空!", Toast.LENGTH_SHORT)
					.show();
			return false;
		} else if ("".equals(password1)) {
			// 密码不能为空
			Toast.makeText(RegisterAct.this, "密码不能为空!", Toast.LENGTH_SHORT)
					.show();
			return false;
		} else if (!password1.equals(password2)) {
			Toast.makeText(RegisterAct.this, "两次密码输入不一致！", Toast.LENGTH_SHORT)
					.show();
			return false;
		}
		return true;
	}

	Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if ("success".equals(msg.obj)) {
				// 表示用户注册成功
				AlertDialog.Builder builder = new Builder(
						RegisterAct.this);
				builder.setMessage("恭喜你！注册成功");
				builder.setTitle("提示");
				builder.setPositiveButton("确认", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						Intent i = new Intent(RegisterAct.this,
								LoginAct.class);
						dialog.dismiss();
						open_activity(HomePage_Activity.class);
						MyPreference.getInstance(RegisterAct.this).SetLoginName(uNameEditText.getText().toString());
						MyPreference.getInstance(RegisterAct.this).SetPassword(pwdEditText1.getText().toString());
MyPreference.getInstance(RegisterAct.this).SetIsSavePwd(true);
						finish();
					}
				}).show();
			} else if ("userExist".equals(msg.obj)) {
				// 用户已经被注册
				AlertDialog.Builder builder = new Builder(
						RegisterAct.this);
				builder.setMessage("抱歉！该用户已被注册！");
				builder.setTitle("提示");
				builder.setPositiveButton("确认", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						dialog.dismiss();
					}
				}).show();
			}
			
		}
		
	};
	/**
	 * 注册
	 */
	private void register() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				userName = uNameEditText.getText().toString();
				password1 = pwdEditText1.getText().toString();
				password2 = pwdEditText2.getText().toString();
				questStr = "{REGISTER:{username:'" + userName + "',password1:'"
						+ password1 + "',password2:'" + password1 + "'}}";
				System.out.println("====questStr====" + questStr);
				System.out.println("====postStr====" + postStr);
				try {
					HttpParams httpParams = new BasicHttpParams();
					// 设置连接超时
					int timeoutConnection = 3000;
					HttpConnectionParams.setConnectionTimeout(httpParams,
							timeoutConnection);
					DefaultHttpClient httpClient = new DefaultHttpClient(
							httpParams);
					HttpPost httpPost = new HttpPost(url);
					List<NameValuePair> nvps = new ArrayList<NameValuePair>();
					nvps.add(new BasicNameValuePair("username", userName));
					nvps.add(new BasicNameValuePair("password1", password1));
					nvps.add(new BasicNameValuePair("password2", password1));
					httpPost.setEntity(new UrlEncodedFormEntity(nvps,
							HTTP.UTF_8));
					HttpResponse response = httpClient.execute(httpPost);
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();
					String isUser = ConvertStreamToString(is);
                    Message message = new Message();   
                    message.obj=isUser;
                    
					System.out.println(isUser);
					myHandler.sendMessage(message);
				
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}).start();

	}

	// 读取字符流
	public String ConvertStreamToString(InputStream is) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String returnStr = "";
		try {
			while ((returnStr = br.readLine()) != null) {
				sb.append(returnStr);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		final String result = sb.toString();

		System.out.println(result);
		return result;
	}
}
