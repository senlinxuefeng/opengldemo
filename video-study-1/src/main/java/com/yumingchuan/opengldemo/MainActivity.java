package com.yumingchuan.opengldemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yumingchuan.opengldemo.base.BaseActivity;
import com.yumingchuan.opengldemo.chapter1.Chapter1Activity;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private LinearLayout mChapterContainer;
    public static HashMap<Integer, Class> mChapterList = new HashMap<>();

    static {
        mChapterList.put(0, Chapter1Activity.class);
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
