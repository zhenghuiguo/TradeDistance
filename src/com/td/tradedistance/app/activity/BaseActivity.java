package com.td.tradedistance.app.activity;

import com.td.tradedistance.app.utils.ProgressUtils;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public abstract class BaseActivity extends FragmentActivity {
	public ProgressUtils proDailog = null;
	protected InputMethodManager inputMethodManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		proDailog = ProgressUtils.createDialog(BaseActivity.this);
		initVariables();
		initViews(savedInstanceState);
		loadData();
	}
    @Override
    protected void onResume() {
    	
    	super.onResume();
    }
	protected void hideSoftKeyboard() {
		if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
			if (getCurrentFocus() != null)
				inputMethodManager.hideSoftInputFromWindow(
						getCurrentFocus().getWindowToken(),
						InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	protected abstract void initVariables();

	protected abstract void initViews(Bundle savedInstanceState);

	protected abstract void loadData();
}
