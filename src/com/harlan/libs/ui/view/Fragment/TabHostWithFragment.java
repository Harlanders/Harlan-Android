package com.harlan.libs.ui.view.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 使用TabHost管理Fragment
 */
public abstract class TabHostWithFragment extends Fragment {

	private View view;

	public abstract int getLayoutId();

	public abstract void initialize(View view);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(getLayoutId(), container, false);

			initialize(view);
		}

		ViewGroup parent = (ViewGroup) view.getParent();
		if (parent != null) {
			parent.removeView(view);
		}
		return view;
	}
}
