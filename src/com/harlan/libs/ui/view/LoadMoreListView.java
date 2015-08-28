package com.harlan.libs.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class LoadMoreListView extends ListView implements OnScrollListener {

	private int lastVisibleItem;
	private int totalItemCount;
	private View footer;

	public LoadMoreListView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setOnScrollListener(this);
	}

	public LoadMoreListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LoadMoreListView(Context context) {
		this(context, null);
	}

	@Override
	public void addFooterView(View v) {
		footer = v;
		// footer = v.findViewById(footerId);
		super.addFooterView(v);
		hideFooter();

		// showFooter();
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if ((totalItemCount == lastVisibleItem)
				&& (scrollState == SCROLL_STATE_IDLE)) {
			if (canLoadMore) {// 能否加载更多
				if (!isLoading) {// 是否正在加载
					isLoading = true;
					showFooter();
					if (listener != null) {
						listener.onLoadMore(footer);
					}
				}
			}

		}

	}

	/**
	 * 加载完成
	 */
	public void loaded() {
		isLoading = false;
		hideFooter();
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.totalItemCount = totalItemCount;
		lastVisibleItem = firstVisibleItem + visibleItemCount;
	}

	/**
	 * 显示footer
	 */
	public void showFooter() {
		footer.setVisibility(VISIBLE);
		footer.setPadding(0, 0, 0, 0);
	}

	/**
	 * 隐藏footer
	 */
	public void hideFooter() {
		footer.setVisibility(GONE);
		footer.setPadding(0, -footer.getHeight(), 0, 0);
	}

	public interface OnLoadMoreListener {
		public void onLoadMore(View footer);
	}

	private OnLoadMoreListener listener;

	public void setOnLoadMoreListener(OnLoadMoreListener listener) {
		this.listener = listener;
	}

	private boolean canLoadMore = true;

	/**
	 * 设置可以加载更多
	 */
	public void setLoadMore() {
		canLoadMore = true;
	}

	/**
	 * 取消加载更多
	 */
	public void cancelLoadMore() {
		canLoadMore = false;
		loaded();
	}

	private boolean isLoading;
}
