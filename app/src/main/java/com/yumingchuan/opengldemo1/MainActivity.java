package com.yumingchuan.opengldemo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yumingchuan.opengldemo1.base.BaseActivity;
import com.yumingchuan.opengldemo1.chapter1.Chapter1Activity;
import com.yumingchuan.opengldemo1.chapter2.Chapter2Activity;
import com.yumingchuan.opengldemo1.chapter3.Chapter3Activity;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private TextView mChapter1;
    private LinearLayout mChapterContainer;
    public static HashMap<Integer, Class> mChapterList = new HashMap<>();

    static {
        mChapterList.put(0, Chapter1Activity.class);
        mChapterList.put(1, Chapter2Activity.class);
        mChapterList.put(2, Chapter3Activity.class);
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
        mChapter1 = findViewById(R.id.chapter1);
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
