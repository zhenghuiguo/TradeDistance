package com.td.tradedistance.app.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.activity.LoginActivity;
import com.td.tradedistance.app.global.Global;


public class ToastUtil {
	private static Context mcontext;

	public static void failedTip(int ret, Context context) {
		mcontext = context;
		switch (ret) {
		case Global.dec_failed:
			TipToast(Global.show_dec_failed);
			break;
		case Global.push_success:
			TipToast(Global.show_push_success);
			break;
		case Global.param_error:
			TipToast(Global.show_param_error);
			break;
		case Global.sys_no_exist:
			TipToast(Global.show_sys_no_exist);
			break;
		case Global.execute_error:
			TipToast(Global.show_execute_error);
			break;
		case Global.error_sqm:
			TipToast(Global.show_error_sqm);
			goLogin(mcontext);
			break;
		case Global.account_no_open:
			TipToast(Global.show_account_no_open);
			break;
		case Global.user_or_pass_error:
			TipToast(Global.show_user_or_pass_error);
			break;
		case Global.no_file:
			TipToast(Global.no_file_info);
			break;
		case Global.no_quan_xian:
			TipToast(Global.no_quan_xian_info);
			break;
		case Global.no_banding_zdtsm:
			TipToast(Global.no_bangding_zdtsm_info);
			break;
		case Global.no_lixiang:
			TipToast(Global.no_lixiang_info);
			break;
		case Global.sjhyzc:
			TipToast(Global.show_sjhyzc);
			break;
		case Global.fsyzmsb:
			TipToast(Global.show_fsyzmsb);
			break;
		}
	}

	public static void TipToast(String tip) {
		Toast.makeText(TDApp.getContext(), tip, Toast.LENGTH_SHORT).show();
	}

	public static void noDataTip() {
		Toast toast = Toast.makeText(TDApp.getContext(), "暂无数据",
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 340);
		toast.show();
	}

	public static void noDataTip1() {
		Toast toast = Toast.makeText(TDApp.getContext(), "无数据",
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 340);
		toast.show();
	}

	/*public static void JfTip(String str) {
		if(!"".equals(str)&&!"0".equals(str)){
			Toast toast = Toast.makeText(TDApp.getContext(), "",
					Toast.LENGTH_SHORT);
			//LinearLayout toastView = (LinearLayout) toast.getView();
			View view = View.inflate(TDApp.getContext(), R.layout.jifenmaopao, null);
			toast.setGravity(Gravity.CENTER, 0, 0);
			TextView tv = new TextView(HSApp.getContext());
			tv.setText("+" + str);
			//toastView.addView(tv, 0);
			TextView tv = (TextView)view.findViewById(R.id.tv_jf);
			//tv.setText("获取 "+str+" 积分 ");
			tv.setText("+"+str);
			toast.setView(view);
			toast.show();
		}
	}
*/
	public static void goLogin(Context context) {
		mcontext = context;
		// Globals.deleteAll();
		Intent it = new Intent(context, LoginActivity.class);
		it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		mcontext.startActivity(it);
	}

	/*public static void SearchTip() {
		Toast toast = Toast.makeText(TDApp.getContext(), "搜索不能空",
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 340);
		toast.show();
	}*/

	public static void Tel(Context context, String phone_number) {
		Uri uri = Uri.parse("tel:" + phone_number);
		Intent it = new Intent(Intent.ACTION_DIAL, uri);
		context.startActivity(it);
	}
}
