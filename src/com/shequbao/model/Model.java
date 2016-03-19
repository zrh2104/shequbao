package com.shequbao.model;

import com.shequbao.R;

/**
 * 静态变量的存放
 * */

public class Model {

	// 网络交互地址前段
	public static String HTTPURL = "http://xgcweb.nat123.net:24022";
	// 店铺获取地址
	public static String SHOPURL = "shop.php?";
	// 签到获取地址
	public static String SELECTSIGNURL = "sign.php";
	// 店铺列表图片前段地址
	public static String SHOPLISTIMGURL = "";
	// 下载签到图片
	public static String SIGNLISTIMGURL = "http://534429149.haoqie.net/liuxiaowei/sign/";
	// 团购获取地址
	public static String TUANURL = "tuan.php?";
	// 添加签到地址
	public static String SIGNURL = "addsign.php";
	// 店铺详情地址
	public static String SHOPDETAILURL = "detailshop.php?";
	// 第一个listview的图片数据数组
	public static int[] LISTVIEWIMG = new int[] {
			R.drawable.ic_category_2147483648, R.drawable.ic_category_10,
			R.drawable.ic_category_20, com.shequbao.R.drawable.ic_category_30,
			R.drawable.ic_category_45, R.drawable.ic_category_50,
			R.drawable.ic_category_55, R.drawable.ic_category_60,
			R.drawable.ic_category_65, R.drawable.ic_category_70,
			R.drawable.ic_category_80, R.drawable.ic_category_none };
	// 第一个listview的文本数据数组
	public static String[] LISTVIEWTXT = new String[] { "热门分类", "美食", "购物",
			"休闲娱乐", "运动健身", "丽人", "结婚", "酒店", "爱车", "亲子", "生活服务", "家装" };
	// 第二个listview的文本数据
	public static String[][] MORELISTTXT = {
			{ "全部分类", "小吃快餐", "咖啡厅", "电影院", "KTV", "茶馆", "足疗按摩", "超市/便利店",
					"银行", "经济型酒店", "景点/郊游", "公园", "美发" },
			{ "全部美食", "小吃快餐", "西餐", "火锅", "北京菜", "川菜", "日本", "面包甜点", "粤菜",
					"韩国料理", "自助餐", "浙江菜", "云南菜", "湘菜", "东南亚菜", "西北菜", "鲁菜",
					"东北菜", "素菜", "新疆菜", "海鲜", "清真菜", "贵州菜", "湖北菜", "其他" },
			{ "全部购物", "综合商场", "服饰鞋包", "超市/便利店", "特色集市", "品牌折扣店", "眼镜店", "珠宝饰品",
					"化妆品", "运动户外", "食品茶酒", "书店", "数码产品", "药店", "京味儿购物", "亲子购物",
					"花店", "家具建材", "更多购物场所" },
			{ "全部休闲娱乐", "咖啡厅", "KTV", "景点/郊游", "电影院", "酒吧", "公园", "温泉", "文化艺术",
					"足疗按摩", "洗浴", "茶馆", "游乐游艺", "密室", "采摘/农家乐", "桌面游戏", "台球馆",
					"DIY手工坊", "休闲网吧", "真人CS", "棋牌室", "轰趴馆", "私人影院", "更多休闲娱乐" },
			{ "全部运动健身", "健身中心", "游泳馆", "瑜伽", "羽毛球馆", "台球馆", "舞蹈", "体育场馆",
					"高尔夫场", "网球场", "武术场馆", "篮球场", "保龄球馆", "足球场", "乒乓球馆",
					"更多体育运动" },
			{ "全部丽人", "美发", "美容/SPA", "齿科", "美甲", "化妆品", "瑜伽", "瘦身纤体", "舞蹈",
					"个性写真", "整形" },
			{ "全部结婚", "婚纱摄影", "婚宴酒店", "婚纱礼服", "婚庆公司", "婚戒首饰", "个性写真", "彩妆造型",
					"婚礼小礼品", "婚礼跟拍", "婚车租赁", "司仪主持", "婚房装修", "更多婚礼服务" },
			{ "全部酒店", "经济型酒店", "五星级酒店", "度假村", "四星级酒店", "三星级酒店", "农家院",
					"公寓式酒店", "青年旅社", "精品酒店", "更多酒店住宿" },
			{ "全部爱车", "维修保养", "驾校", "停车场", "4S店/汽车销售", "加油站", "配件/车饰", "汽车租赁",
					"汽车保险" },
			{ "全部亲子", "亲子摄影", "幼儿教育", "亲子游乐", "孕产护理", "亲子购物", "更多亲子服务" },
			{ "全部生活服务", "医院", "银行", "齿科", "宠物", "培训", "快照/冲印", "学校", "旅行社",
					"购物网站", "干洗店", "家政", "奢侈品护理", "商务楼", "小区", "更多生活服务" },
			{ "全部家装", "家具家装", "家用电器", "建材", "家装卖场", "装修设计" } };
	// shoplist中toplist文本
	public static String[] SHOPLIST_TOPLIST = new String[] { "全部商户", "团购商户",
			"可外卖商户", "会员卡商户", "优惠券商户", "新增商户" };
	// shoplist中排序文本
	public static String[] SHOPLIST_THREELIST = { "默认排序", "距离最近", "人气最高",
			"评价最好", "口味最佳", "环境最雅", "服务最好", "费用最低", "费用最高" };
	// shoplist中商区文本
	public static String[] SHOPLIST_PLACE = new String[] { "附近", "全城热门商区",
			"新港城", "杜浔", "沙西", "古雷" ,"霞美","旧镇"};
	// 美食全部地区数组2
	public static String[][] SHOPLIST_PLACESTREET = new String[][] {
			{ "500米", "1000米", "2000米", "5000米" },
			{ "全部商区", "俯盛一期",""},
			{ "所有区域","四区", "五区", "九区", "十区", "十四区", "十五区","十六区","十七区"
				,"十八区","十九区","二十区","二十一区","一期","二期","三期"},
			{ "所有区域", "镇政府", "文卿村", "范阳村", "正阳村", "后因村", "徐坎村","过洋村","院边村"
					,"路打村","林仓村","湖里村"},
			{ "所有区域",  },
			{ "所有区域", "古城村", "杏仔村", "半湖村", "岱仔村", "庙前村", },
			{ "所有区域",  },
			{ "所有区域",  },
			};
	// 排行榜中toplist数据
	public static String[] PAIHANGBANG_TOPLIST = new String[] { "餐厅排行榜",
			"休闲娱乐排行榜", "购物排行榜", "丽人排行榜", "运动健身排行榜", "酒店排行榜", "生活服务排行榜" };
	// 表情的数据
	public static int image1[] = { R.drawable.emoji000, R.drawable.emoji001,
			R.drawable.emoji002, R.drawable.emoji003, R.drawable.emoji004,
			R.drawable.emoji005, R.drawable.emoji006, R.drawable.emoji007,
			R.drawable.emoji008, R.drawable.emoji009, R.drawable.emoji010,
			R.drawable.emoji011, R.drawable.emoji012, R.drawable.emoji013,
			R.drawable.emoji014, R.drawable.emoji015, R.drawable.emoji016,
			R.drawable.emoji017, R.drawable.emoji018, R.drawable.emoji019,
			R.drawable.ic_key_delete_normal };

	public static int image2[] = { R.drawable.emoji020, R.drawable.emoji021,
			R.drawable.emoji022, R.drawable.emoji023, R.drawable.emoji024,
			R.drawable.emoji025, R.drawable.emoji026, R.drawable.emoji027,
			R.drawable.emoji101, R.drawable.emoji102, R.drawable.emoji103,
			R.drawable.emoji104, R.drawable.emoji105, R.drawable.emoji106,
			R.drawable.emoji107, R.drawable.emoji108, R.drawable.emoji109,
			R.drawable.emoji110, R.drawable.emoji201, R.drawable.emoji202,
			R.drawable.ic_key_delete_normal };
	public static int image3[] = { R.drawable.emoji203, R.drawable.emoji204,
			R.drawable.emoji205, R.drawable.emoji206, R.drawable.emoji207,
			R.drawable.emoji208, R.drawable.emoji209, R.drawable.emoji210,
			R.drawable.emoji211, R.drawable.emoji212, R.drawable.emoji213,
			R.drawable.emoji214, R.drawable.emoji215, R.drawable.emoji216,
			R.drawable.emoji217, R.drawable.emoji218, R.drawable.emoji219,
			R.drawable.emoji220, R.drawable.emoji221, R.drawable.emoji222,
			R.drawable.ic_key_delete_normal };
	public static int image4[] = { R.drawable.emoji301, R.drawable.emoji302,
			R.drawable.emoji303, R.drawable.emoji304, R.drawable.emoji305,
			R.drawable.emoji306, R.drawable.emoji307, R.drawable.emoji308,
			R.drawable.emoji309, R.drawable.emoji310, R.drawable.emoji311,
			R.drawable.emoji312, R.drawable.emoji313, R.drawable.emoji314,
			R.drawable.emoji315, R.drawable.emoji316,
			R.drawable.ic_key_delete_normal };

}
