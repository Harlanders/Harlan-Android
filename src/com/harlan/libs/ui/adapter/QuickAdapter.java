package com.harlan.libs.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class QuickAdapter<T> extends BaseAdapter {
	protected List<T> data;
	private Context context;
	private int layoutId;

	public QuickAdapter(Context context, List<T> data, int layoutId) {
		super();
		this.context = context;
		this.data = data;
		this.layoutId = layoutId;
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = ViewHolder.get(context, convertView, parent,
				layoutId, position);
		conver(holder, getItem(position));
		return holder.getConverView();
	}

	protected abstract void conver(ViewHolder holder, T t);
}
