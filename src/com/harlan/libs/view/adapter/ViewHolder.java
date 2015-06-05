package com.harlan.libs.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {

	private static int mPosition;
	private final SparseArray<View> views;
	private View convertView;

	// private View mConvertView;

	private ViewHolder(Context context, View convertView, ViewGroup parent,
			int layoutId) {
		// this.convertView = convertView;
		views = new SparseArray<View>();
		// mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
		// false);
		convertView = View.inflate(context, layoutId, null);
		convertView.setTag(this);
	}

	public static ViewHolder get(Context context, View convertView,
			ViewGroup parent, int layoutId) {
		if (convertView == null) {
			return new ViewHolder(context, convertView, parent, layoutId);
		} else {
			return (ViewHolder) convertView.getTag();
		}

	}

	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {
		View view = views.get(viewId);
		if (view == null) {
			view = convertView.findViewById(viewId);
			views.put(viewId, view);
		}

		return (T) view;
	}

	public View getConverView() {
		return convertView;
	}

	public int getPosition() {
		return mPosition;
	}

	/**
	 * 为TextView设置字符串
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int viewId, String text) {
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setImageResource(int viewId, int drawableId) {
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);

		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
		ImageView view = getView(viewId);
		view.setImageBitmap(bm);
		return this;
	}

}
