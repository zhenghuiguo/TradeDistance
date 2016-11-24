package com.td.tradedistance.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.adapter.XiaoXiZhongXinAdapter;
import com.td.tradedistance.app.utils.Logger;

public class WoDeGongGaoCourseFragment extends Fragment{
	private static final String Tag ="MainActivity";// MyFragment.class.getSimpleName();
	private ListView mCourseList;
    private XiaoXiZhongXinAdapter adapter;
	/*@Override
	public void onAttach(Context context) {
		Logger.d(Tag,"onAttach MyFragment");
		super.onAttach(context);
	}*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Logger.d(Tag,"onCreate MyFragment");
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tongzhigonggao,container, false);
		Logger.d(Tag,"onCreateView MyFragment");
		mCourseList= (ListView) view.findViewById(R.id.wanjiecourselist_lv);
		adapter = new XiaoXiZhongXinAdapter(getActivity());
		mCourseList.setAdapter(adapter);
		mCourseList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			     Logger.d("onclick", "onItemClick");
			}
		});
		return view;
	}
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	Logger.d(Tag,"onActivityCreated MyFragment");
    	super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStart() {
    	Logger.d(Tag,"onStart MyFragment");
    	super.onStart();
    }
    @Override
    public void onResume() {
    	Logger.d(Tag,"onResume MyFragment");
    	super.onResume();
    }
    @Override
    public void onPause() {
    	Logger.d(Tag,"onPause MyFragment");
    	super.onPause();
    }
    @Override
    public void onStop() {
    	Logger.d(Tag,"onStop MyFragment");
    	super.onStop();
    }
    @Override
    public void onDestroyView() {
    	Logger.d(Tag,"onDestroyView MyFragment");
    	super.onDestroyView();
    }
    @Override
    public void onDestroy() {
    	Logger.d(Tag,"onDestroy MyFragment");
    	super.onDestroy();
    }
    @Override
    public void onDetach() {
    	Logger.d(Tag,"onDetach MyFragment");
    	super.onDetach();
    }
}
