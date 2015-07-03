package com.harlan.libs.ui.ViewGroup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.harlan.libs.R;

/**
 * 卫星菜单
 */
public class ArcMenu extends ViewGroup implements OnClickListener {

	private static final int POS_LEFT_TOP = 0;
	private static final int POS_LEFT_BOTTOM = 1;
	private static final int POS_RIGHT_TOP = 2;
	private static final int POS_RIGHT_BOTTOM = 3;

	private static final int RADIUS = 100;

	public enum Position {
		LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM
	}

	public enum Status {
		OPEN, CLOSE
	}

	private Position position = Position.RIGHT_BOTTOM;
	private Status status = Status.CLOSE;
	private int radius;

	private OnItemClickListener onItemClickListener;

	private View centerView;

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public ArcMenu(Context context) {
		this(context, null);
	}

	public ArcMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ArcMenu(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		init(context, attrs, defStyleAttr);
	}

	@SuppressLint("NewApi")
	public ArcMenu(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	private void init(Context context, AttributeSet attrs, int defStyleAttr) {
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.ArcMenu, defStyleAttr, 0);

		int count = ta.getIndexCount();

		for (int i = 0; i < count; i++) {
			int attr = ta.getIndex(i);

			if (attr == R.styleable.ArcMenu_ArcMenu_Radius) {
				radius = (int) ta.getDimension(attr, TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_DIP, RADIUS, getResources()
								.getDisplayMetrics()));
			} else if (attr == R.styleable.ArcMenu_ArcMenu_Position) {
				int pos = ta.getInt(attr, POS_RIGHT_BOTTOM);
				position = getPosition(pos);
			}
		}

		ta.recycle();
	}

	private Position getPosition(int pos) {
		switch (pos) {
		case POS_LEFT_TOP:
			return Position.LEFT_TOP;
		case POS_LEFT_BOTTOM:
			return Position.LEFT_BOTTOM;
		case POS_RIGHT_TOP:
			return Position.RIGHT_TOP;
		case POS_RIGHT_BOTTOM:
			return Position.RIGHT_BOTTOM;
		}
		return Position.RIGHT_BOTTOM;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int count = getChildCount();

		for (int i = 0; i < count; i++) {
			measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		if (changed) {
			layoutCenterView();
			layoutItemViews();
		}

	}

	private void layoutItemViews() {
		int count = getChildCount();

		double alpha = Math.PI / 2 / (count - 2);
		int cl;
		int ct;

		for (int i = 0; i < (count - 1); i++) {
			cl = (int) (radius * Math.sin(alpha * i));
			ct = (int) (radius * Math.cos(alpha * i));

			View view = getChildAt(i + 1);
			view.setVisibility(View.GONE);
			int height = view.getMeasuredHeight();
			int width = view.getMeasuredWidth();

			if ((position == Position.LEFT_BOTTOM)
					|| (position == Position.RIGHT_BOTTOM)) {
				ct = getMeasuredHeight() - ct - height;
			}

			if ((position == Position.RIGHT_TOP)
					|| (position == Position.RIGHT_BOTTOM)) {
				cl = getMeasuredWidth() - cl - width;
			}
			view.layout(cl, ct, cl + width, ct + height);
		}
	}

	private void layoutCenterView() {
		centerView = getChildAt(0);
		centerView.setOnClickListener(this);

		int l = 0;
		int t = 0;

		int height = centerView.getMeasuredHeight();
		int width = centerView.getMeasuredWidth();

		switch (position) {
		case LEFT_TOP:
			break;
		case LEFT_BOTTOM:
			t = getMeasuredHeight() - height;
			break;
		case RIGHT_TOP:
			l = getMeasuredWidth() - width;
			break;
		case RIGHT_BOTTOM:
			l = getMeasuredWidth() - width;
			t = getMeasuredHeight() - height;
			break;
		}

		centerView.layout(l, t, l + width, t + height);
	}

	public interface OnItemClickListener {
		void onClick(View view, int position);
	}

	@Override
	public void onClick(View v) {
		rotateCenterView(v);
		toggleMenu(300);
	}

	private void toggleMenu(int duration) {
		int count = getChildCount();
		for (int i = 0; i < (count - 1); i++) {
			final View view = getChildAt(i + 1);
			view.setVisibility(View.VISIBLE);

			int cl = (int) (radius * Math.sin((Math.PI / 2 / (count - 2)) * i));
			int ct = (int) (radius * Math.cos((Math.PI / 2 / (count - 2)) * i));

			int xflag = 1;
			int yflag = 1;

			if ((position == Position.LEFT_TOP)
					|| (position == Position.LEFT_BOTTOM)) {
				xflag = -1;
			}

			if ((position == Position.LEFT_TOP)
					|| (position == Position.RIGHT_TOP)) {
				yflag = -1;
			}

			AnimationSet animset = new AnimationSet(true);
			Animation tranAnim = null;

			// to open
			if (status == Status.CLOSE) {
				tranAnim = new TranslateAnimation(xflag * cl, 0, yflag * ct, 0);
				view.setClickable(true);
				view.setFocusable(true);

			} else
			// to close
			{
				tranAnim = new TranslateAnimation(0, xflag * cl, 0, yflag * ct);
				view.setClickable(false);
				view.setFocusable(false);
			}
			tranAnim.setFillAfter(true);
			tranAnim.setDuration(duration);
			tranAnim.setStartOffset((i * 100) / count);

			// 旋转动画
			RotateAnimation rotateAnim = new RotateAnimation(0, 720,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			rotateAnim.setDuration(duration);
			rotateAnim.setFillAfter(true);

			animset.addAnimation(rotateAnim);
			animset.addAnimation(tranAnim);
			animset.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
				}

				@Override
				public void onAnimationRepeat(Animation animation) {
				}

				@Override
				public void onAnimationEnd(Animation animation) {
					if (status == Status.CLOSE) {
						view.setVisibility(View.GONE);
					}
				}
			});
			view.startAnimation(animset);

			final int pos = i + 1;
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (onItemClickListener != null) {
						onItemClickListener.onClick(view, pos);
					}

					menuItemAnim(pos - 1);
					changeStatus();

				}
			});
		}
		changeStatus();
	}

	private void menuItemAnim(int pos) {
		for (int i = 0; i < (getChildCount() - 1); i++) {

			View childView = getChildAt(i + 1);
			if (i == pos) {
				childView.startAnimation(scaleBigAnim(300));
			} else {

				childView.startAnimation(scaleSmallAnim(300));
			}

			childView.setClickable(false);
			childView.setFocusable(false);

		}

	}

	private Animation scaleSmallAnim(int duration) {

		AnimationSet animationSet = new AnimationSet(true);

		ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		AlphaAnimation alphaAnim = new AlphaAnimation(1f, 0.0f);
		animationSet.addAnimation(scaleAnim);
		animationSet.addAnimation(alphaAnim);
		animationSet.setDuration(duration);
		animationSet.setFillAfter(true);
		return animationSet;

	}

	private Animation scaleBigAnim(int duration) {
		AnimationSet animationSet = new AnimationSet(true);

		ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 4.0f, 1.0f, 4.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		AlphaAnimation alphaAnim = new AlphaAnimation(1f, 0.0f);

		animationSet.addAnimation(scaleAnim);
		animationSet.addAnimation(alphaAnim);

		animationSet.setDuration(duration);
		animationSet.setFillAfter(true);
		return animationSet;

	}

	private void changeStatus() {
		status = status == Status.CLOSE ? Status.OPEN : Status.CLOSE;
	}

	private void rotateCenterView(View v) {
		float start = 0;
		float end = 360;
		if (status == Status.CLOSE) {
			start = 360;
			end = 0;
		}
		RotateAnimation animation = new RotateAnimation(start, end,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animation.setDuration(300);
		animation.setFillAfter(true);
		v.startAnimation(animation);
	}

	public boolean isOpen() {
		return status == Status.OPEN;
	}
}
