package com.yumingchuan.opengldemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yumingchuan.opengldemo.base.BaseActivity;
import com.yumingchuan.opengldemo.listen1.Listen1Activity;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private LinearLayout mListenContainer;
    public static HashMap<Integer, Class> mListenList = new HashMap<>();

    static {
        mListenList.put(0, Listen1Activity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mListenContainer = findViewById(R.id.listen_container);
        for (int i = 0; i < mListenList.size(); i++) {
            View mView = LayoutInflater.from(this).inflate(R.layout.layout_item_listen, null);
            TextView mListenName = mView.findViewById(R.id.listen_name);
            mListenName.setText("第" + (i + 1) + "课");
            mListenContainer.addView(mView);
        }
    }

    private void initListener() {
        for (int i = 0; i < mListenContainer.getChildCount(); i++) {
            View mView = mListenContainer.getChildAt(i);
            mView.setTag(i);
            mView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        Class mClass = mListenList.get(index);
        if (mClass != null) {
            Intent mIntent = new Intent(this, mClass);
            startActivity(mIntent);
        }
    }
}
