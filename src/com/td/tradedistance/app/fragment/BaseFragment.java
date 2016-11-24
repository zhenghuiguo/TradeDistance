package com.td.tradedistance.app.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public abstract class BaseFragment extends Fragment{
	
	    protected InputMethodManager inputMethodManager;
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);
	        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
	        initVariables();
	        initViews(savedInstanceState);
	        loadData();
	    }
	    
	    protected void hideSoftKeyboard() {
	        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
	            if (getActivity().getCurrentFocus() != null)
	                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
	                        InputMethodManager.HIDE_NOT_ALWAYS);
	        }
	    }
	    
    protected abstract void initVariables();  
    protected abstract void initViews(Bundle savedInstanceState);  
    protected abstract void loadData();
}
