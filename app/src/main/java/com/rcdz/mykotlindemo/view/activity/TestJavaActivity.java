package com.rcdz.mykotlindemo.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.rcdz.mykotlindemo.R;
import com.rcdz.mykotlindemo.tools.GlideApp;
import com.rcdz.mykotlindemo.view.customview.BottomDialog;
import com.wjt.mylibrary.base.BaseActivity;

import org.jetbrains.annotations.NotNull;

/**
 * 专门用来转换kotlin 用的
 * @author:create by
 * 邮箱 983049539@qq.com
 */
public class TestJavaActivity extends BaseActivity {
    public static final int requestCode=0x001;
    private static final int REQUEST_CODE =0x002 ;
    ImageView imageView;
    int a;
    int b;
    @Override
    public void initData() {
        Button imageselected=fvbi(R.id.button);
        Button button2=findViewById(R.id.button2);
        Button button3=findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent intent=new Intent(TestJavaActivity.this,WebVietestActivity.class);
        TestJavaActivity.this.startActivity(intent);
            }
        });
        imageselected.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void initView() {
            //限数量的多选(比如最多9张)
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
        switch (view.getId()){
            case R.id.button: //图片上传
                openActivity(SelectPictureActivity.class);
                break;
            case R.id.button3: //获取通信录
                openActivity(GetTxlActivity.class);
                break;
        }
    }
}
