package com.rcdz.mykotlindemo.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionListener;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.rcdz.mykotlindemo.R;
import com.rcdz.mykotlindemo.tools.GlideApp;
import com.rcdz.mykotlindemo.tools.NotifitionTools;
import com.rcdz.mykotlindemo.view.customview.BottomDialog;
import com.wjt.mylibrary.base.BaseActivity;
import com.wjt.mylibrary.utils.GlobalToast;

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
        Button button4=findViewById(R.id.button4);
        Button dialog=findViewById(R.id.dialog);
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
        imageselected.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        dialog.setOnClickListener(this);
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

                for(int i=0;i<100;i++){

                    int finalI = i;
                    new Handler().postDelayed(new Runnable() {
                      @Override
                      public void run() {
                          NotifitionTools.showNotificationProgressApkDown(TestJavaActivity.this, finalI);
                      }
                  },1000);

                }

                break;
            case R.id.dialog: //dialog
                    openActivity(DialogListActivity.class);

                break;
        }
    }
}
