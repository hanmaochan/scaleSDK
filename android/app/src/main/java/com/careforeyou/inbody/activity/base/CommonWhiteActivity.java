package com.careforeyou.inbody.activity.base;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.careforeyou.inbody.R;
import com.careforeyou.inbody.utils.ScreenUtils;

import java.text.NumberFormat;


public class CommonWhiteActivity extends SimpleActivity implements View.OnClickListener {
    LinearLayout titleLayout;
    public ImageView backImg;
    public TextView titleText;
    public TextView backTv;
    public TextView rightTv;
    LinearLayout contentLayout;
    TextView loadText;
    public LinearLayout backLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_white);
        initView();
        ScreenUtils.setScreenFullStyle(this, Color.WHITE);
    }

    private void initView() {
        titleLayout = (LinearLayout) findViewById(R.id.titleLayout);
        backImg = (ImageView) findViewById(R.id.backImg);
        titleText = (TextView) findViewById(R.id.title_tv);
        backTv = (TextView) findViewById(R.id.back_tv);
        rightTv = (TextView) findViewById(R.id.right_tv);
        contentLayout = (LinearLayout) findViewById(R.id.contentLayout);
        backLl = findViewById(R.id.back_Ll);
        backLl.setOnClickListener(this);
        rightTv.setOnClickListener(this);

    }

    public void setLoadingVisibility(int visibility) {
        loadText.setVisibility(visibility);
    }

    public void setTitleLayoutVisibility(int visibility) {
        titleLayout.setVisibility(visibility);
    }


    public void setBackeIcon(int id) {
        backImg.setImageResource(id);
    }

    public void setTitleText(String title) {
        titleText.setText(title);
    }

    @Override
    public void onClick(View view) {
        if (view == rightTv) {
            onRightClick(view);
        } else {
            onOtherClick(view);
        }
    }

    protected void onOtherClick(View v) {

    }

    protected void onRightClick(View v) {

    }

    protected void setContentSubView(int layoutId, String title) {
        View view = LayoutInflater.from(this).inflate(layoutId, contentLayout, false);
        contentLayout.addView(view);
        if (!TextUtils.isEmpty(title))
            titleText.setText(title);

    }
}
