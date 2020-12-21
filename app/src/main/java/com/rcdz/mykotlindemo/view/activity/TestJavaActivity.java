package com.rcdz.mykotlindemo.view.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.race604.drawable.wave.WaveDrawable;
import com.rcdz.mykotlindemo.Mytest;
import com.rcdz.mykotlindemo.R;
import com.rcdz.mykotlindemo.tools.NotifitionTools;
import com.wjt.mylibrary.base.BaseActivity;
import com.wjt.mylibrary.utils.GlobalToast;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * 专门用来转换kotlin 用的
 * @author:create by
 * 邮箱 983049539@qq.com
 */
public class TestJavaActivity extends BaseActivity {
    public static final int requestCode=0x001;
    private static final int REQUEST_CODE =0x002 ;
    ImageView imageView2;
    int a;
    int b;
    @Override
    public void initData() {
        long time=new Date().getTime();
        Log.i("test",time+"");
        Button imageselected=fvbi(R.id.button);
        Button button2=findViewById(R.id.button2);
        Button button3=findViewById(R.id.button3);
        Button button4=findViewById(R.id.button4);
        Button dialog=findViewById(R.id.dialog);
        Button button8=findViewById(R.id.button8);
        Button button5=findViewById(R.id.button5);
        Button button10=findViewById(R.id.button10);
        Button button9=findViewById(R.id.button9);
        Button button13=findViewById(R.id.button13);
        Button button14=findViewById(R.id.button14);
        Button button15=findViewById(R.id.button15);
        LikeButton button_dianzan=findViewById(R.id.button_dianzan);
         imageView2=findViewById(R.id.imageView2);
        Button picture_lodding=findViewById(R.id.picture_lodding);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.CAMERA
                ), new CheckRequestPermissionsListener() {
                    @Override
                    public void onAllPermissionOk(Permission[] allPermissions) {
                        Intent intent=new Intent(TestJavaActivity.this,WebVietestActivity.class);
                        TestJavaActivity.this.startActivity(intent);
                    }

                    @Override
                    public void onPermissionDenied(Permission[] refusedPermissions) {

                    }
                });


            }
        });


        button_dianzan.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                GlobalToast.show4("点赞啦",Toast.LENGTH_LONG);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                GlobalToast.show4("取消点赞啦",Toast.LENGTH_LONG);
            }
        });
        imageselected.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        dialog.setOnClickListener(this);
        button5.setOnClickListener(this);
        picture_lodding.setOnClickListener(this);
        button8.setOnClickListener(this);
        button10.setOnClickListener(this);
        button9.setOnClickListener(this);
        button13.setOnClickListener(this);
        button14.setOnClickListener(this);
        button15.setOnClickListener(this);
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
            case R.id.button4: //获取通信录
                GlobalToast.show("testadada adafd",Toast.LENGTH_LONG);
                GlobalToast.showPicture("娃哈哈", Toast.LENGTH_LONG);
                break;
            case R.id.dialog: //dialog
                    openActivity(DialogListActivity.class);

                break;
            case R.id.button5: //通知栏
                new Thread(new Runnable() {
                    int i=0;
                    @Override
                    public void run() {
                        for(int i=0;i<100;i++){
                                NotifitionTools.showNotificationProgressApkDown(TestJavaActivity.this, i);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                        }
                    }
                }).start();
                break;
            case R.id.picture_lodding: //图片进度条
//                Drawable drawable= getResources().getDrawable(R.mipmap.woaini);
                WaveDrawable mWaveDrawable = new WaveDrawable(this,R.drawable.woaini);
//                mWaveDrawable.setWaveSpeed(100);//设置波动速度（以像素为单位）
//                mWaveDrawable.setWaveAmplitude(50);//设置波幅（以像素为单位）
//                mWaveDrawable.setWaveLength(100);//设置波长（以像素为单位）
                mWaveDrawable.setIndeterminate(true);//如进度条一样，如果以不确定模式运行，则会一遍又一遍地增加水位
//                mWaveDrawable.setLevel(200);
                imageView2.setImageDrawable(mWaveDrawable);

                break;

            case R.id.button8: //java测试类
                openActivity(Mytest.class);
                break;
            case R.id.button10://系统进度条
                openActivity(ProgressBarTest.class);
                break;
            case R.id.button9://日期选择器
                openActivity(DatePicActivitry.class);//todo 这个框架不行，重新来一个日期选择器
                break;
            case R.id.button13://蓝牙
                openActivity(BlutoothActivity.class);//todo  https://www.jianshu.com/p/795bb0a08beb 闲了完成它
                break;
            case R.id.button14://Navigation 指示器
                openActivity(NavigationActivity.class);
                break;
            case R.id.button15://mationLayout
                openActivity(MationLayoutActivity.class);
                break;

        }
    }
}
