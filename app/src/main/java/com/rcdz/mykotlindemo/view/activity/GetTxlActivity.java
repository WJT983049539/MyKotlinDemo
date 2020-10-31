package com.rcdz.mykotlindemo.view.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcdz.mykotlindemo.R;
import com.rcdz.mykotlindemo.model.adapter.TxlAdapter;
import com.rcdz.mykotlindemo.model.bean.MyContacts;
import com.rcdz.mykotlindemo.tools.ContactUtils;
import com.wjt.mylibrary.base.BaseActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kotlin.jvm.Synchronized;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/31 13:53
 */
public class GetTxlActivity extends BaseActivity {
    private static final int PERMISS_CONTACT = 1;
    TxlAdapter txlAdapter;
    private RecyclerView rc_txl;
    private ImageView img_back;
    List<MyContacts> list= Collections.synchronizedList(new ArrayList<>());

    Handler txlHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==0x001){
                txlAdapter.notifyDataSetChanged();
            }
        }
    };
    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        rc_txl=fvbi(R.id.rc_txl);
        img_back=fvbi(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetTxlActivity.this.finish();
            }
        });
        list.clear();
        MyContacts myContacts=new MyContacts();
        myContacts.setName("该手机没有通讯录");
        myContacts.setPhone("");
        list.add(myContacts);
        txlAdapter=new TxlAdapter(this,list,R.layout.item_txl);
        rc_txl.setLayoutManager(new LinearLayoutManager(this));
        rc_txl.setAdapter(txlAdapter);
        inintData();
    }

    private void inintData() {
        String[] permissList = {Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE};
        addPermissByPermissionList(GetTxlActivity.this, permissList, PERMISS_CONTACT);
    }
    private void showContacts() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MyContacts>  contacts= ContactUtils.getAllContacts(GetTxlActivity.this);
                if(contacts.size()==0){
                }else{
                    list.clear();
                    list.addAll(contacts);
                    Message message=new Message();
                    message.what=0x001;
                    txlHandler.sendMessage(message);
                }

            }
        }).start();




    }


    @OnClick({R.id.img_back, R.id.rc_txl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
        }
    }

    /**
     * 动态权限
     */
    public void addPermissByPermissionList(Activity activity, String[] permissions, int request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   //Android 6.0开始的动态权限，这里进行版本判断
            ArrayList<String> mPermissionList = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++) {
                if (ContextCompat.checkSelfPermission(activity, permissions[i])
                        != PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(permissions[i]);
                }
            }
            if (mPermissionList.isEmpty()) {  //非初次进入App且已授权
                showContacts();
                Toast.makeText(this, "已授权", Toast.LENGTH_SHORT).show();
            } else {
                //请求权限方法
                String[] permissionsNew = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
                ActivityCompat.requestPermissions(activity, permissionsNew, request); //这个触发下面onRequestPermissionsResult这个回调
            }
        }
    }

    /**
     * requestPermissions的回调
     * 一个或多个权限请求结果回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasAllGranted = true;
        //判断是否拒绝  拒绝后要怎么处理 以及取消再次提示的处理
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                hasAllGranted = false;
                break;
            }
        }
        if (hasAllGranted) { //同意权限做的处理,开启服务提交通讯录
            showContacts();
            Toast.makeText(this, "同意授权", Toast.LENGTH_SHORT).show();
        } else {    //拒绝授权做的处理，弹出弹框提示用户授权
            dealwithPermiss(GetTxlActivity.this, permissions[0]);
        }

    }

    public void dealwithPermiss(final Activity context, String permission) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(GetTxlActivity.this);
            builder.setTitle("操作提示")
                    .setMessage("注意：当前缺少必要权限！\n请点击“设置”-“权限”-打开所需权限\n最后点击两次后退按钮，即可返回")
                    .setPositiveButton("去授权", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", context.getApplicationContext().getPackageName(), null);
                            intent.setData(uri);
                            context.startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(GetTxlActivity.this, "取消操作", Toast.LENGTH_SHORT).show();
                        }
                    }).show();

        }
    }
    @NotNull
    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_txl;
    }

    @Override
    public void onClick(View view) {

    }
}
