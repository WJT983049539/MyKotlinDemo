package com.rcdz.mykotlindemo.view.activity

import android.app.AlertDialog
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import com.rcdz.mykotlindemo.R
import com.rcdz.mykotlindemo.view.customview.dialog.BottomDialog
import com.rcdz.mykotlindemo.view.customview.dialog.LoadingManager
import com.rcdz.mykotlindemo.view.customview.dialog.LoddingDialog
import com.rcdz.mykotlindemo.view.customview.dialog.PuTongDialog
import com.rcdz.mykotlindemo.view.customview.dialog.PuTongDialog.DialogConfirmClick
import com.wjt.mylibrary.base.BaseActivity


/**
 * 作用:各种dialog 无线增加 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/20 17:08
 */
 class DialogListActivity : BaseActivity() {
    override fun initData() {

    }
    override fun initView() {
        val designbutton: Button =findViewById(R.id.dialog_tishi)
        val button12: Button =findViewById(R.id.button12)
        val dialog_lodding: Button =findViewById(R.id.dialog_lodding)
        designbutton.setOnClickListener(this)
        val dialog_putong: Button =findViewById(R.id.dialog_putong)
        dialog_putong.setOnClickListener(this)
        val button6: Button =findViewById(R.id.button6)
        button6.setOnClickListener(this)
        dialog_lodding.setOnClickListener(this)
        button12.setOnClickListener(this)
    }
    override fun setNowActivityName(): String {
        return ""
    }

    override fun setLayout(): Int {
        return R.layout.dialoglist
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.dialog_tishi -> {  //提示弹框
                val loadingManager = LoadingManager(this)
                loadingManager.show()
                Handler().postDelayed({
                    loadingManager.hideSuccess(
                        "加载成功"
                    ) { }
                }, 1000)

                Handler().postDelayed({ loadingManager.hideError("加载失败") {} }, 5000)
            }
            R.id.dialog_putong -> { //普通弹框

                val puTongDialog = PuTongDialog(this, "", object : DialogConfirmClick {
                    override fun ConfirmClick() {}
                    override fun NoConfirmClick() {}
                })
                puTongDialog.show2()

            }
            R.id.button6 -> { //底部弹框
                var bottomDialog = BottomDialog();
                bottomDialog.ShowBottomDialog(this)
            }
            R.id.dialog_lodding -> {
                var loddingDialog = LoddingDialog(this, "提示奥")
                loddingDialog.show2()
            }
            R.id.button12 -> {
                val alertDialog = AlertDialog.Builder(this)
                    .setTitle("发布任务")
                    .setMessage("确定要发布任务？发布后开始执行任务")
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("确定") { dialogInterface, i ->
                        //添加"Yes"按钮
                        Log.i("test", "点击了确定按钮")
                    }
                    .setNegativeButton(
                        "取消"
                    ) { dialogInterface, i ->
                        //添加取消
                        Log.i("test", "点击了取消按钮")
                    }
                    .create()
                alertDialog.show()
                //放在show()之后，不然有些属性是没有效果的，比如height和width

                //放在show()之后，不然有些属性是没有效果的，比如height和width

                val metrics2 = resources.displayMetrics
                val width = metrics2.widthPixels
                val dialogWindow: Window = alertDialog.getWindow()!!
                val p: WindowManager.LayoutParams = dialogWindow.getAttributes() // 获取对话框当前的参数值
                // 设置宽度
                // 设置宽度
                p.width = (width * 0.95).toInt() // 宽度设置为屏幕的0.95

                p.gravity = Gravity.CENTER //设置位置
                //p.alpha = 0.8f;//设置透明度
                //p.alpha = 0.8f;//设置透明度
                dialogWindow.setAttributes(p)

            }

        }


    }
}