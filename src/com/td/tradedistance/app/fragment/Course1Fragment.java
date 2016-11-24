package com.td.tradedistance.app.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.adapter.Course1Adapter;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.widget.TitleBar;

public class Course1Fragment extends Fragment{
	private static final String Tag = Course1Fragment.class.getSimpleName();
	private TitleBar mTitleBar;
	private ListView mCourseList;
    private Course1Adapter adapter;
    private Handler mHandler;
    private int mListViewHeight;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View courseLayout = inflater.inflate(R.layout.fragment_course1, container, false);
		mHandler = new Handler();
		mTitleBar = (TitleBar) courseLayout.findViewById(R.id.titleBar);
		mCourseList= (ListView) courseLayout.findViewById(R.id.courselist_lv);
		adapter = new Course1Adapter(getActivity());
		mCourseList.setAdapter(adapter);
		mCourseList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			     Logger.d("onclick", "onItemClick");
			}
		});
		mCourseList.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
	            @Override
	            public void onGlobalLayout() {
	                mListViewHeight = mCourseList.getHeight();
	                mCourseList.getViewTreeObserver().removeGlobalOnLayoutListener(this);
	              /*  if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
	                	mCourseList.getViewTreeObserver().removeOnGlobalLayoutListener(this);
	                } else {
	                	mCourseList.getViewTreeObserver().removeGlobalOnLayoutListener(this);
	                }*/
	            }
	        });
		 mCourseList.setOnScrollListener(new AbsListView.OnScrollListener() {
		        @Override
		        public void onScrollStateChanged(AbsListView view, int scrollState) {

		        }

		        @Override
		        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		            if (firstVisibleItem == 0) {
		                View firstVisibleItemView = mCourseList.getChildAt(0);
		                if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0) {
		                    Logger.d("ListView", "<----滚动到顶部----->");
		                }
		            } else if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
		                View lastVisibleItemView = mCourseList.getChildAt(mCourseList.getChildCount() - 1);
		                if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == mListViewHeight) {
		                    Logger.d("ListView", "#####滚动到底部######");
		                }
		            }
		        }
		    });
		scrollUp();
		return courseLayout;
	}
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	Logger.d(Tag,"onActivityCreated CourseFragment");
    	super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStart() {
    	Logger.d(Tag,"onStart CourseFragment");
    	super.onStart();
    }
    @Override
    public void onResume() {
    	Logger.d(Tag,"onResume CourseFragment");
    	
    	super.onResume();
    }
    @Override
    public void onPause() {
    	Logger.d(Tag,"onPause CourseFragment");
    	super.onPause();
    }
    @Override
    public void onStop() {
    	Logger.d(Tag,"onStop CourseFragment");
    	super.onStop();
    }
    @Override
    public void onDestroyView() {
    	Logger.d(Tag,"onDestroyView CourseFragment");
    	super.onDestroyView();
    }
    @Override
    public void onDestroy() {
    	Logger.d(Tag,"onDestroy CourseFragment");
    	super.onDestroy();
    }
    @Override
    public void onDetach() {
    	Logger.d(Tag,"onDetach CourseFragment");
    	super.onDetach();
    }
    private void scrollUp(){
		
  		mHandler.post(new Runnable() {
  			
  			@Override
  			public void run() {
  				mCourseList.smoothScrollToPosition(0);
  				mCourseList.setSelection(0);
  			}
  		});
  	}
    
   
}
