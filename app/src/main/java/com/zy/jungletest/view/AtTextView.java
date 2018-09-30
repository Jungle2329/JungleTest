package com.zy.jungletest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zy.jungletest.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jungle
 * @Description 自动识别开头结尾关键字
 * @Date 2018/7/27
 **/

public class AtTextView extends android.support.v7.widget.AppCompatTextView {

    private final static String TAG = "AtTextView";
    private String regularExpression = "@[\\s\\S]+?;";
    private int keywordsColor;

    public AtTextView(Context context) {
        super(context);
        init();
    }

    public AtTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }

    public AtTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AtTextView);
        keywordsColor = ta.getColor(R.styleable.AtTextView_keywordsColor, getResources().getColor(R.color.white));
        if (!TextUtils.isEmpty(ta.getString(R.styleable.AtTextView_regularExpression))) {
            regularExpression = ta.getString(R.styleable.AtTextView_regularExpression);
        }
        ta.recycle();
    }

    private void init() {
        String contentStr = getText().toString();
        //筛选出需要的字符
        ArrayList<String> atList = new ArrayList<>();
        Matcher matcher = Pattern.compile(regularExpression).matcher(contentStr);
        while (matcher.find()) {
            String str = matcher.group();
            atList.add(str);
            Log.i(TAG, str);
        }
        //修改选出字符的样式
        SpannableStringBuilder ssb = new SpannableStringBuilder(contentStr);
        int keyWordIndex;//关键字开始位置
        int keyWordLength;//关键字长度
        int keyWordEndIndex;//关键字结束位置
        for (final String keyWords : atList) {
            keyWordIndex = contentStr.indexOf(keyWords);
            keyWordLength = keyWords.length();
            keyWordEndIndex = keyWordIndex + keyWordLength;
            //设置关键字点击事件
            ssb.setSpan(new Clickable(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), keyWords, Toast.LENGTH_SHORT).show();
                }
            }), keyWordIndex, keyWordEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//设置点击事件

        }
        //适用样式
        setText(ssb);
        setHighlightColor(Color.TRANSPARENT);//去掉点击效果
        setMovementMethod(LinkMovementMethod.getInstance());//必须设置否则无效
    }

    /**
     * 内部类，用于截获点击富文本后的事件
     */
    class Clickable extends ClickableSpan {
        private final View.OnClickListener mListener;

        private Clickable(View.OnClickListener mListener) {
            this.mListener = mListener;
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onClick(v);
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(keywordsColor);
            ds.setUnderlineText(false);    //去除超链接的下划线
        }
    }

}
