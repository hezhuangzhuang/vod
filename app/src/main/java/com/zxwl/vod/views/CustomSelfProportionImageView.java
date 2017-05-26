package com.zxwl.vod.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.zxwl.vod.R;


/**
 * 自定义尺寸比例ImageView
 */
@SuppressLint("NewApi")
public class CustomSelfProportionImageView extends AppCompatImageView {
	private float w = 1; // 宽比例
	private float h = 1; // 高比例

	public CustomSelfProportionImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CustomSelfProportionImageView);
		this.h = typedArray.getFloat(R.styleable.CustomSelfProportionImageView_h, 1);
		this.w = typedArray.getFloat(R.styleable.CustomSelfProportionImageView_w, 1);
		typedArray.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		if (width > 0 && w > 0 && h > 0) {
			int newH = (int) (width * h / w);
			setMeasuredDimension(width, newH);
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}

	}

}
