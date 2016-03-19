package com.shequbao;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shequbao.base.BaseActivity;
import com.shequbao.fragment.fragment_home_page;
import com.shequbao.fragment.fragment_luntan_page;
import com.shequbao.fragment.fragment_me_page;
import com.shequbao.fragment.fragment_pinche_page;

public class HomePage_Activity extends BaseActivity implements
		fragment_luntan_page.BackHandlerInterface {

	private RadioGroup myTabRg;
	private Fragment mepage_content;
	private Fragment homepage_content;
	private Fragment shop_content;
	private Fragment linlipage_content;
	private Fragment tegongpage_content;
	private fragment_luntan_page selectedFragment;
	private Boolean isexit = false;
    private RadioButton home_buttion;
    private RadioButton shop_buttion;
    private RadioButton luntan_buttion;
    private RadioButton tegong_buttion;
    private RadioButton me_buttion;
private Handler myhandler=new Handler();
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("activity=====>onCreate");
		setContentView(R.layout.activity_homepage);
		home_buttion= (RadioButton) findViewById(R.id.activity_homepage_homepage);
		shop_buttion= (RadioButton) findViewById(R.id.activity_homepage_shop);
		luntan_buttion= (RadioButton) findViewById(R.id.activity_homepage_linli);
		tegong_buttion= (RadioButton) findViewById(R.id.activity_homepage_tegong);
		me_buttion= (RadioButton) findViewById(R.id.activity_homepage_me);
setbuttonsize();
		initView();

	}

	private void setbuttonsize() {
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("activity=====>onResume");

		isjump();

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("activity=====>onStart");

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("activity=====>onPause");

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("activity=====>onStop");

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("activity=====>onDestroy");

	}

	// 底部导航条逻辑1
	public void initView() {
		System.out.println("测试从这边开始");
		homepage_content = new fragment_home_page();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.activity_homepage_content, homepage_content)
				.commit();
		myTabRg = (RadioGroup) findViewById(R.id.activity_homepage_tabmenu);
		// 监听导航条按钮的checked的状态
		myTabRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.activity_homepage_homepage:
					homepage_content = new fragment_home_page();
					getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.activity_homepage_content,
									homepage_content).commit();
					selectedFragment = null;
					break;
				case R.id.activity_homepage_shop:
//					shop_content = new fragment_shop_page();
//					getSupportFragmentManager()
//							.beginTransaction()
//							.replace(R.id.activity_homepage_content,
//									shop_content).commit();
					selectedFragment = null;
					open_activity(ShopListActivity.class);

					break;
				case R.id.activity_homepage_linli:
					linlipage_content = new fragment_luntan_page();
					getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.activity_homepage_content,
									linlipage_content).commit();

					break;
				case R.id.activity_homepage_tegong:
					tegongpage_content = new fragment_pinche_page();
					getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.activity_homepage_content,
									tegongpage_content).commit();
					selectedFragment = null;

					break;
				case R.id.activity_homepage_me:
					mepage_content = new fragment_me_page();
					getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.activity_homepage_content,
									mepage_content).commit();
					selectedFragment = null;

					break;
				default:
					break;
				}

			}
		});
	}

	// 判断是否需要跳转到制定的fragment页面
	private void isjump() {
		// 获取传递过来的封装了数据的Bundle

		Bundle bundle = HomePage_Activity.this.getIntent().getExtras();
		// 判断传过来bundle是否为null
		if (bundle != null) {
			Boolean boolean1 = bundle.getBoolean("boolean");
			if (boolean1.equals(true)) {
				System.out.println("执行跳转");
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.activity_homepage_content, mepage_content)
						.commit();

			}
		}

	}

	@Override
	public void setSelectedFragment(fragment_luntan_page backHandledFragment) {
		this.selectedFragment = backHandledFragment;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (selectedFragment != null) {
			selectedFragment.onBackPressed();
			System.out.println("这边显示１");
		} else if (selectedFragment == null && !isexit) {
			showtoast(this, "再按一次退出");
			isexit = true;
			//3秒内再按一次回退键退出
			myhandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					isexit = false;
				}
			}, 3000);

		} else if (selectedFragment == null && isexit) {
			super.onBackPressed();

		}

	}

}
