package com.nanke.cook.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 自定义listView 解决listView 嵌套gridview 显示不全问题
 * 
 * @author Administrator
 *
 */

public class FixedListView extends ListView {

	public FixedListView(Context context) {
		super(context);
	}

	public FixedListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FixedListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	@Override
	protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX,
			boolean clampedY) {
		super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
	}
	
	

}
