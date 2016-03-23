package com.shequbao.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shequbao.model.Model;
import com.shequbao.model.PingCheInfo;
import com.shequbao.R;
import com.shequbao.tools.LoadImg;
import com.shequbao.tools.LoadImg.ImageDownloadCallBack;

/**
 * 拼车列表的适配器
 */

public class PingCheAdapter extends BaseAdapter {

	private List<PingCheInfo> list;
	private Context ctx;
	private LoadImg loadImg;

	public PingCheAdapter(List<PingCheInfo> list, Context ctx) {
		this.list = list;
		this.ctx = ctx;
		// 实例化获取图片的类
		loadImg = new LoadImg(ctx);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		final Holder hold;
		if (arg1 == null) {
			hold = new Holder();
			arg1 = View.inflate(ctx, R.layout.item_pinche, null);
			hold.suser = (TextView) arg1.findViewById(R.id.pingche_username);
			hold.miusericon = (ImageView) arg1.findViewById(R.id.pingche_user_icon);
			hold.sstarttime = (TextView) arg1.findViewById(R.id.pingche_starttime);
			hold.sstartdest = (TextView) arg1.findViewById(R.id.pingche_startdes);
			hold.sseatplace = (TextView) arg1.findViewById(R.id.pingche_place_Textview);
			hold.mseaticon = (ImageView) arg1.findViewById(R.id.pingche_place_icon_nums);
			hold.senddest = (TextView) arg1.findViewById(R.id.pingche_enddes);
			hold.sdistance = (TextView) arg1.findViewById(R.id.pingche_distance);
			hold.mImage=(ImageView) arg1.findViewById(R.id.pingche_user_icon);
			arg1.setTag(hold);
		} else {
			hold = (Holder) arg1.getTag();
		}
		hold.suser.setText(list.get(arg0).getSuser());
		hold.mseaticon.setTag(Model.SHOPLISTIMGURL + list.get(arg0).getIusericon());
		hold.sstarttime.setText(list.get(arg0).getSstarttime());
		hold.sstartdest.setText(list.get(arg0).getSstartdest());
		hold.sseatplace.setText("剩余"+list.get(arg0).getSseat()+"个座位");
		hold.senddest.setText(list.get(arg0).getSenddest());
		if (list.get(arg0).getSdistance()!=null) {
			hold.sdistance.setText("距离我"+list.get(arg0).getSdistance()+"km");
		}

		int slevel = Integer.valueOf(list.get(arg0).getSseat());
		switch (slevel) {
		case 5:
			hold.mseaticon.setImageResource(R.drawable.star0);
			break;
		case 4:
			hold.mseaticon.setImageResource(R.drawable.star1);
			break;
		case 3:
			hold.mseaticon.setImageResource(R.drawable.star2);
			break;
		case 2:
			hold.mseaticon.setImageResource(R.drawable.star3);
			break;
		case 1:
			hold.mseaticon.setImageResource(R.drawable.star4);
			break;
		case 0:
			hold.mseaticon.setImageResource(R.drawable.star5);
			break;
		}

		// 设置默认显示的图片
		hold.mImage.setImageResource(R.drawable.pingche_driver_icon);
		
		// 网络获取图片
		Bitmap bit = loadImg.loadImage(hold.mImage, Model.SHOPLISTIMGURL
				+ list.get(arg0).getIusericon(), new ImageDownloadCallBack() {
			@Override
			public void onImageDownload(ImageView imageView, Bitmap bitmap) {
				// 网络交互时回调进来防止错位
				if (hold.mImage.getTag().equals(
						Model.SHOPLISTIMGURL + list.get(arg0).getIusericon())) {
					// 设置网络下载回来图片显示
					hold.mImage.setImageBitmap(bitmap);
				}
			}
		});
		// 从本地获取的
		if (bit != null) {
			// 设置缓存图片显示
			hold.mImage.setImageBitmap(bit);
		}

		return arg1;
	}

	static class Holder {
		TextView suser, sstarttime, sstartdest, senddest,sdistance,sseatplace;
		ImageView miusericon, mseaticon,mImage;
	}

}
