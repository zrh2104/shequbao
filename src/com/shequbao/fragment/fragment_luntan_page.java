package com.shequbao.fragment;

import com.shequbao.HomePage_Activity;
import com.shequbao.R;
import com.shequbao.base.MyV4Fragment;

import android.R.string;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.ScrollView;

public class fragment_luntan_page extends MyV4Fragment {
	private WebView webview;
	private View view;
	private boolean mHandledPress = false;
	private boolean mHandledPress１ = false;
	protected BackHandlerInterface backHandlerInterface;// 定义接口
	private ProgressBar progressbar;
	private ScrollView scrollview;
private String url="http://gulei.cc/?m=3g";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_linli_page, null);
		webview = (WebView) view.findViewById(R.id.luntan_webview);
		webview.loadUrl(url);
		progressbar = (ProgressBar) view.findViewById(R.id.luntan_ProgressBar);
		scrollview = (ScrollView) view.findViewById(R.id.luntan_scrollview);
		init();
		return view;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 其实在onAttach()回调时就已经把Fragment与Activity绑定在了一起，所以只要生命流程在onAttach()之后的任意一个生命周期,我们都可以通过getActivity来获取Activity的实例，来进行强制转换，
		backHandlerInterface = (BackHandlerInterface) getActivity();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// 在onstart 生命周期中传递自己的实例对象
		backHandlerInterface.setSelectedFragment(this);
	}

	private void init() {
		WebSettings wSet = webview.getSettings();
		wSet.setJavaScriptEnabled(true);
		// 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
		webview.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				System.out.println("第一次");
				if (webview.getContentHeight() != 0) {
					// 这个时候网页才显示
					progressbar.setVisibility(View.GONE);
					// 加载完成后网页滚动到最顶部的位置
					scrollview.scrollTo(0, 0);

				}
			}

			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

		});
		webview.setWebChromeClient(new WebChromeClient() {
			// 设置加载网页时显示进度条
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
				progressbar.setVisibility(View.VISIBLE);
				progressbar.setProgress(newProgress);
				System.out.println("这里newprogress 是" + newProgress);
			}
		});

	}

	// 回调成功时执行这个函数
	public void onBackPressed() {
			System.out.println("这里显示fragment_luntan 被回调");
			webview.goBack();
			String a=webview.getUrl();
			//判断获取到的当前页面是否和首页的地址相同相同则退出
			if (a.equals(url)&&!mHandledPress１){
				mHandledPress１=true;
				showtoast(getActivity(), "再按一次退出");
			//３秒内再按一次回退键退出应用
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						mHandledPress１=false;

					}
				}, 3000);
				//3秒内按回退就退出
			}else if (a.equals(url)&&mHandledPress１) {
				getActivity().finish();}}
	

	// 定义一个接口用于传递给HomePage_Activity fragment_luntan_page自己的实例对象
	public interface BackHandlerInterface {
		public void setSelectedFragment(fragment_luntan_page backHandledFragment);
	}

}
