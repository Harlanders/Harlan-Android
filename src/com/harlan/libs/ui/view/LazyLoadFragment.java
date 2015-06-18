package com.harlan.libs.ui.view;

import android.support.v4.app.Fragment;

/**
 * ViewPager和Fragment同时使用，防止预加载
 * 
 * <p>
 * Do not use {@link #lazyLoad()} in your first fragment,it will be call before
 * {@link #onCreateView()}
 * 
 * @author Administrator
 *
 */
public abstract class LazyLoadFragment extends Fragment {
	/** Fragment当前状态是否可见 */
	protected boolean isVisible;

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if (getUserVisibleHint()) {
			if (isVisible) {
				// 只加载一次
			} else {
				isVisible = true;
				onVisible();
			}

		} else {
			// isVisible = false;
			onInvisible();
		}
	}

	/**
	 * 可见
	 */
	protected void onVisible() {
		lazyLoad();
	}

	/**
	 * 不可见
	 */
	protected void onInvisible() {

	}

	/**
	 * 延迟加载 子类必须重写此方法
	 */
	protected abstract void lazyLoad();
}
