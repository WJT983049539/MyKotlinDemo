package com.wjt.mylibrary.base

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

/**
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */

abstract class BaseActivity : AppCompatActivity(),  View.OnClickListener { //todo 还未完善
    init {
        Log.i("test", "BaseActivity")
    }

    var clickTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //全屏显示
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val window = window
        val layoutParams = window.attributes
        //隐藏底部键盘，一直不会弹出
        layoutParams.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
        //屏幕常亮
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(setLayout())
        Log.i("test","在"+ setNowActivityName()+"onCreate里面")
        initData()
        initView()
    }
    abstract fun initData():Unit
    abstract fun initView():Unit
    //抽象的方法,必须实现
    abstract fun setNowActivityName():String
    abstract fun setLayout():Int

    override fun onRestart() {
        super.onRestart()
        Log.i("test","在"+ setNowActivityName()+"onRestart里面")
    }

    override fun onResume() {
        super.onResume()
        Log.i("test","在"+ setNowActivityName()+"onResume里面")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("test","在"+ setNowActivityName()+"onDestroy里面")
    }

    override fun onStop() {
        super.onStop()
        Log.i("test","在"+ setNowActivityName()+"onStop里面")
    }
}