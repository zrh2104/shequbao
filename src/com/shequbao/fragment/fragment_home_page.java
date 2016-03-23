package com.shequbao.fragment;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shequbao.R;
import com.shequbao.tools.ImageCycleView;
import com.shequbao.tools.ImageCycleView.ImageCycleViewListener;
import com.shequbao.tools.MyApplication;

public class fragment_home_page extends android.support.v4.app.Fragment {
	private com.shequbao.tools.ImageCycleView mAdView;
	private ArrayList<String> viewpager_arraylist_image_url;
	public String url ="/xgcsq/firstpageviewpager";
	public ImageView imageview;
	public ImageView[] imageviews;
	public View[] viewitemlist;//
	public View view;
	public ImageView[] imageviewlist;
	public RequestQueue mQueue;
	private Boolean isthis=true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("onCreate");
		url=getString(R.string.host_address)+url;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 获取fragment_homepage页面的view对象
		view = inflater.inflate(R.layout.fragment_homepage, null);

		// 获取 viewpager对象
		mAdView = (ImageCycleView) view
				.findViewById(R.id.homepage_head_viewpage);
		// 创建一个RequestQueue对象用于加入volley 网络请求队列
		mQueue = Volley.newRequestQueue(getActivity());
		// 获取服务器４个pager 滑动图片的url并且加载入imageview
		System.out.println("onCreateView");
		getimageurl();

		return view;

	}

	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(int position, View imageView) {
			// TODO 单击图片处理事件
			Toast.makeText(getActivity(), "position->" + position, 0).show();
		}

		@Override
		public void displayImage(String imageURL, ImageView imageView) {
			com.nostra13.universalimageloader.core.ImageLoader.getInstance()
					.displayImage(imageURL, imageView);// 此处本人使用了ImageLoader对图片进行加装！
		}
	};

	// 用volly的jsonobjectRequest 获取服务器的json数据
	private void getimageurl() {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, url, null, new Listener<JSONObject>() {

					@Override
					// 服务器返回的response数据在这个接口回调中获取
					public void onResponse(JSONObject response) {
						try {
							jsondata_to_arraylist(response);

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						if (isthis) {
							Toast.makeText(getActivity(), "网络异常",
									Toast.LENGTH_SHORT).show();
							
						}
						System.out.println("长度是" + mQueue.getCache().get(url));
						// 获取缓存数据
						if (mQueue.getCache().get(url) != null) {// 判断缓存是否存在
							String cachedResponse = new String(mQueue
									.getCache().get(url).data);
							try {
								// 使用缓存数据载入图片
								JSONObject jsoncache = new JSONObject(
										cachedResponse);
								jsondata_to_arraylist(jsoncache);

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
		// jsonObjectRequest请求加入RequestQueue队列
		mQueue.add(jsonObjectRequest);

	}

	// 将服务器获取的json数据取出来后转化成地址数组
	private void jsondata_to_arraylist(JSONObject response)
			throws JSONException {
		// 获取的response key为url的value值并转化为字符串
		String geturl = response.get("url").toString();
		// 去掉第一个和最后一个字符
		geturl = geturl.substring(1, geturl.length() - 1);
		// 以“，”为界分割成数组
		String[] b = geturl.split(",");
		viewpager_arraylist_image_url = new ArrayList<>();
		// 循环加入到viewpager_arraylist_image_url 数组中
		for (int i = 0; i < b.length; i++) {

			viewpager_arraylist_image_url.add(b[i].trim());
		}
		mAdView.setImageResources(viewpager_arraylist_image_url,
				mAdCycleViewListener);

	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("onStart");

	}
@Override
public void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	System.out.println("onResume");
	mAdView.startImageCycle();

}
@Override
public void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	System.out.println("onPause");
	mAdView.pushImageCycle();

}
@Override
public void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
	System.out.println("onStop");

}
@Override
public void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	System.out.println("onDestroy");
	mAdView.pushImageCycle();
isthis=false;
}
}
