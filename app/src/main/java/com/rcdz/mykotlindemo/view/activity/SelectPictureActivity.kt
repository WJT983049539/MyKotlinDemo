package com.rcdz.mykotlindemo.view.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.donkingliang.imageselector.utils.ImageSelector
import com.qw.soul.permission.SoulPermission
import com.qw.soul.permission.bean.Permission
import com.qw.soul.permission.bean.Permissions
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener
import com.rcdz.mykotlindemo.R
import com.rcdz.mykotlindemo.model.adapter.PictureAdapter
import com.rcdz.mykotlindemo.model.bean.UserDataLoadMore
import com.wjt.mylibrary.base.BaseActivity
import kotlinx.android.synthetic.main.activity_refresh.*
import java.util.*


/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/31 9:25
 */
class SelectPictureActivity : BaseActivity() {
    val REQUEST_CODE=0x001;
    lateinit var adapter: PictureAdapter
    lateinit var PictureList:RecyclerView
    var pictures=ArrayList<String>()
    override fun setNowActivityName(): String {
        return "图片选择"
    }

    override fun setLayout(): Int {
        return R.layout.selsected
    }

    override fun onClick(view: View) {
    if (view.id==R.id.startpicture){
        //第一种使用的是ImageSelector
        getPremission2()

    }else if(view.id==R.id.startpicture_and_video){
        //第二种使用的是
    }
    }
    override fun initData() {}
    override fun initView() {
        var  selected=findViewById<Button>(R.id.startpicture)
        var  startpicture_and_video=findViewById<Button>(R.id.startpicture_and_video)
        PictureList=findViewById(R.id.picture_selected)
        selected.setOnClickListener(this)
        startpicture_and_video.setOnClickListener(this)
        PictureList.layoutManager= GridLayoutManager(this,2)
        adapter= PictureAdapter(pictures, this)
        PictureList.adapter=adapter;
    }
    //      /storage/emulated/0/temp/0.5437442147590474.jpg
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            pictures=data?.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            adapter.AddData(pictures)
            /**
             * 是否是来自于相机拍照的图片，
             * 只有本次调用相机拍出来的照片，返回时才为true。
             * 当为true时，图片返回的结果有且只有一张图片。
             */
            val isCameraImage: Boolean =data.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false)

        Log.i("test", "")

        }
    }


    fun getPremission2() {
        SoulPermission.getInstance().checkAndRequestPermissions(
            Permissions.build(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WAKE_LOCK,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_WIFI_STATE
            ),  //if you want do noting or no need all the callbacks you may use SimplePermissionsAdapter instead
            object : CheckRequestPermissionsListener {
                override fun onAllPermissionOk(allPermissions: Array<Permission>) {
                    Log.i("test", "权限获取成功")

                    //限数量的多选(比如最多9张)
                    ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false) //设置是否单选
                        .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
                        //.setSelected(selected)  // 把已选的图片传入默认选中。
                        .canPreview(true) //是否可以预览图片，默认为true
                        .start(this@SelectPictureActivity, REQUEST_CODE) // 打开相册
                }

                override fun onPermissionDenied(refusedPermissions: Array<Permission>) {
                    Toast.makeText(
                        this@SelectPictureActivity, refusedPermissions[0].toString() +
                                " 权限获取失败", Toast.LENGTH_SHORT
                    ).show()
                    AlertDialog.Builder(this@SelectPictureActivity).setTitle("提示")
                        .setMessage("如果你拒绝了权限,应用中的一些功能将不糊能正常使用")
                        .setPositiveButton(
                            "授予权限"
                        ) { dialog, which -> //用户点击以后
                            val ff = PanduanIsProhibitedPermissionDenied(refusedPermissions)
                            if (!ff) {
                                SoulPermission.getInstance().goApplicationSettings {
                                    Log.i(
                                        "test",
                                        "这里是在设置也手动获取到权限以后返回，回调"
                                    )
                                }
                            } else {
                                getPremission2()
                            }
                        }.create().show()
                }
            })
    }

    private fun PanduanIsProhibitedPermissionDenied(refusedPermissions: Array<Permission>): Boolean {
        var flag = true
        for (i in refusedPermissions.indices) {
            if (!refusedPermissions[i].shouldRationale()) {
                flag = false
                return flag
            }
        }
        return flag
    }


}