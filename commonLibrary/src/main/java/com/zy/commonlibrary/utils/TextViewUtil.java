package com.zy.commonlibrary.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextViewUtil {
	private TextViewUtil(){

	}
	/**
	 * 设置TextView中的个别字为其他颜色显示
	 */
	public static void setTextDifferentColor(TextView tv, int start, int end, int color) {
		String str = tv.getText().toString();
		SpannableStringBuilder ss = new SpannableStringBuilder(str);
		ss.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv.setText(ss);
	}
	/**
	 * 设置TextView中的个别字为其他颜色和点击事件
	 *TextView tv 设置字体的控件
	 *int start 字体开始值
	 * int end 字体结束结束值
	 * int color 字体色值
	 * onClickListener 点击事件监听,可以设置null就没有点击事件
	 *float size 字体大小的浮点值
	 */
	public  static void  setTextDifferentColorClick(TextView tv, int start, int end, int color, float size, View.OnClickListener onClickListener) {
		String str = tv.getText().toString();
		SpannableStringBuilder ssb = new SpannableStringBuilder(str);
		ssb.setSpan(new Clickable(onClickListener), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//设置点击事件
		ssb.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//设置字体颜色
		ssb.setSpan(new RelativeSizeSpan(size), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //2.0f表示默认字体大小的两倍
		tv.setText(ssb);
		tv.setHighlightColor(Color.TRANSPARENT);//去掉点击效果
		tv.setMovementMethod(LinkMovementMethod.getInstance());//必须设置否则无效
	}
	/**
	 * 设置TextView中的个别字为其他图片的显示且有点击事件
	 * Context context 上下文
	 *TextView tv 设置字体的控件
	 *int start 字体开始值
	 * int end 字体结束结束值
	 * int drawable 图片的id
	 * int zoomInt 图片缩小的倍数
	 * onClickListener 添加点击事件,可以设置null就没有点击事件
	 */
	public static void setTextDifferentDrawableClick(Context context, TextView tv, int start, int end, int drawable, int zoomInt , View.OnClickListener onClickListener) {
		String str = tv.getText().toString();
		SpannableStringBuilder ssb = new SpannableStringBuilder(str);
		ssb.setSpan(new Clickable(onClickListener), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//设置点击事件
		Drawable drawableImage = context.getResources().getDrawable(drawable);
		drawableImage.setBounds(0, 0, drawableImage.getIntrinsicWidth()/zoomInt, drawableImage.getIntrinsicHeight()/zoomInt);
		ImageSpan imageSpan = new ImageSpan(drawableImage, ImageSpan.ALIGN_BASELINE);
		ssb.setSpan(imageSpan, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		tv.setText(ssb);
		tv.setMovementMethod(LinkMovementMethod.getInstance());//必须设置否则无效
	}

	/**
	 * 内部类，用于截获点击富文本后的事件
	 */
	public static class Clickable extends ClickableSpan implements View.OnClickListener {
		private final View.OnClickListener mListener;

		public Clickable(View.OnClickListener mListener) {
			this.mListener = mListener;
		}

		@Override
		public void onClick(View v) {
			if(mListener != null){
				mListener.onClick(v);
			}
		}
		@Override
		public void updateDrawState(TextPaint ds) {
			ds.setColor(ds.linkColor);
			ds.setUnderlineText(false);    //去除超链接的下划线
		}
	}
	/**
	 * 一个关键字所有内容高亮变色
	 *
	 * @param color
	 *            变化的色值
	 * @param text
	 *            文字
	 * @param keyword
	 *            文字中的关键字
	 * @return
	 */
	public static void matcherSigleSearchSetColor(int color, String text,
                                                  String keyword, TextView textView) {
		SpannableString s = new SpannableString(text);
		Pattern p = Pattern.compile(keyword);
		Matcher m = p.matcher(s);
		while (m.find()) {
			int start = m.start();
			int end = m.end();
			s.setSpan(new ForegroundColorSpan(color), start, end,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		textView.setText(s);
	}

	/**
	 * 多个关键字高亮变色
	 *
	 * @param color
	 *            变化的色值
	 * @param text
	 *            文字
	 * @param keyword
	 *            文字中的关键字数组
	 * @return
	 */
	public static void matcherMoreSearchSetColor(int color, String text,
                                                 String[] keyword, TextView textView) {
		SpannableString s = new SpannableString(text);
		for (int i = 0; i < keyword.length; i++) {
			Pattern p = Pattern.compile(keyword[i]);
			Matcher m = p.matcher(s);
			while (m.find()) {
				int start = m.start();
				int end = m.end();
				s.setSpan(new ForegroundColorSpan(color), start, end,
						Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		}
		textView.setText(s);
	}
}
