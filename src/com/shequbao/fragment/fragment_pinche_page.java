package com.shequbao.fragment;

import java.util.ArrayList;
import java.util.List;

import com.shequbao.R;
import com.shequbao.adapter.PingCheAdapter;
import com.shequbao.model.PingCheInfo;
import com.shequbao.tools.HttpGetThread;
import com.shequbao.tools.MyJson;
import com.shequbao.tools.ThreadPoolUtils;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class fragment_pinche_page extends android.support.v4.app.Fragment {
private ListView pclistview;
private PingCheAdapter mpingcheadapter;
private Boolean listBottemFlag = true;
private MyJson myJson=new MyJson();
private List<PingCheInfo> list = new ArrayList<PingCheInfo>();
private Button ListBottem = null;
private boolean flag = true;
private int mStart = 1;
private int mEnd = 5;
private String url = "/xgcsq/PingCheServlet";

	@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	 View view=	inflater.inflate(R.layout.fragment_pinche_page, null);
	 
	 pclistview=(ListView) view.findViewById(R.id.pingche_Listview);
	 init();
	return view;
	

	
}
	private void init(){
		mpingcheadapter = new PingCheAdapter(list, getActivity());
		
		ListBottem = new Button(getActivity());
		ListBottem.setText("点击加载更多");
		ListBottem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag && listBottemFlag) {
					ThreadPoolUtils.execute(new HttpGetThread(hand, url));
					listBottemFlag = false;
				} else if (!listBottemFlag)
					Toast.makeText(getActivity(), "加载中请稍候", 1).show();
			}
		});
		pclistview.addFooterView(ListBottem, null, false);
		ListBottem.setVisibility(View.GONE);
		pclistview.setAdapter(mpingcheadapter);
		pclistview.setOnItemClickListener(new MainListOnItemClickListener());
		ThreadPoolUtils.execute(new HttpGetThread(hand, url));
	
	}
	//跳转到详情页
		private class MainListOnItemClickListener implements OnItemClickListener {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				Intent intent = new Intent(ShopListActivity.this, ShopDetailsActivity.class);
//				Bundle bund = new Bundle();
//				bund.putSerializable("ShopInfo",list.get(arg2));
//				intent.putExtra("value",bund);
//				startActivity(intent);
			}
		}
	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(getActivity(), "找不到地址", 1).show();
				listBottemFlag = true;
			} else if (msg.what == 100) {
				Toast.makeText(getActivity(), "传输失败", 1).show();
				listBottemFlag = true;
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				// 在activity当中获取网络交互的数据
				if (result != null) {
					// 1次网络请求返回的数据
					System.out.println("传过来的json值是"+result);
					List<PingCheInfo> newList = myJson.getPingCheList(result);
//					System.out.println("传过来的newList值是"+newList+"大小"+newList.size());

					if (newList != null) {
						if (newList.size() == 5) {
							ListBottem.setVisibility(View.VISIBLE);
							mStart += 5;
							mEnd += 5;
						} else {
							ListBottem.setVisibility(View.GONE);
						}
						for (PingCheInfo info : newList) {
					
					
							list.add(info);
						}
						
						mpingcheadapter.notifyDataSetChanged();
						listBottemFlag = true;
						mpingcheadapter.notifyDataSetChanged();
					}
				}
				mpingcheadapter.notifyDataSetChanged();
			}
		};
	};
}
