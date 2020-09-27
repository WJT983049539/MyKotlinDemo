package com.rcdz.mykotlindemo.view.activity;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rcdz.mykotlindemo.R;
import com.rcdz.mykotlindemo.tools.GlideApp;
import com.wjt.mylibrary.base.BaseActivity;

import org.jetbrains.annotations.NotNull;

/**
 * 专门用来转换kotlin 用的
 * @author:create by
 * 邮箱 983049539@qq.com
 */
class TestJavaActivity extends BaseActivity {
    ImageView imageView;
    int a;
    int b;
    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        imageView=findViewById(R.id.imageView);
                GlideApp.with(this)
            .load(R.mipmap.ic_launcher)
            .circleCrop()
            .into(imageView);
    }

    @NotNull
    @Override
    public String setNowActivityName() {
        return null;

    }

    @Override
    public int setLayout() {
        return R.layout.activity_java_test;
    }

    @Override
    public void onClick(View view) {

    }
}
