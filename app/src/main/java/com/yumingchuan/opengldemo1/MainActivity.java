package com.yumingchuan.opengldemo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yumingchuan.opengldemo1.base.BaseActivity;
import com.yumingchuan.opengldemo1.chapter1.Chapter1Activity;
import com.yumingchuan.opengldemo1.chapter2.Chapter2Activity;
import com.yumingchuan.opengldemo1.chapter3.Chapter3Activity;
import com.yumingchuan.opengldemo1.chapter4.Chapter4Activity;
import com.yumingchuan.opengldemo1.chapter5.Chapter5Activity;
import com.yumingchuan.opengldemo1.chapter6.Chapter6Activity;
import com.yumingchuan.opengldemo1.chapter7.Chapter7Activity;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private LinearLayout mChapterContainer;
    public static HashMap<Integer, Class> mChapterList = new HashMap<>();

    static {
        mChapterList.put(0, Chapter1Activity.class);
        mChapterList.put(1, Chapter2Activity.class);
        mChapterList.put(2, Chapter3Activity.class);
        mChapterList.put(3, Chapter4Activity.class);
        mChapterList.put(4, Chapter5Activity.class);
        mChapterList.put(5, Chapter6Activity.class);
        mChapterList.put(6, Chapter7Activity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mChapterContainer = findViewById(R.id.chapter_container);
        for (int i = 0; i < mChapterList.size(); i++) {
            View mView = LayoutInflater.from(this).inflate(R.layout.layout_item_chapter, null);
            TextView mChapterName = mView.findViewById(R.id.chapter_name);
            mChapterName.setText("第" + (i + 1) + "章");
            mChapterContainer.addView(mView);
        }
    }

    private void initListener() {
        for (int i = 0; i < mChapterContainer.getChildCount(); i++) {
            View mView = mChapterContainer.getChildAt(i);
            mView.setTag(i);
            mView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        Class mClass = mChapterList.get(index);
        if (mClass != null) {
            Intent mIntent = new Intent(this, mClass);
            startActivity(mIntent);
        }
    }
}
