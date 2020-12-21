package com.rcdz.mykotlindemo.view.activity

import android.view.View
import com.rcdz.mykotlindemo.R
import com.rcdz.mykotlindemo.model.adapter.RecyclerViewAdapter
import com.rcdz.mykotlindemo.view.customview.ohter.HorizontalProgressBarWithNumber
import com.rcdz.mykotlindemo.view.customview.ohter.NumberProgressBar
import com.wjt.mylibrary.base.BaseActivity

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/27 11:55
 */
class ProgressBarTest : BaseActivity() {

    lateinit var numberProgressBar: NumberProgressBar
    lateinit var numberProgressBar2: HorizontalProgressBarWithNumber
    var i=0;
    override fun initData() {}
    override fun initView() {
        numberProgressBar=fvbi(R.id.numprogress)
        numberProgressBar2=fvbi(R.id.hpbwn)
        ss()
    }
    override fun setNowActivityName(): String {
        return "bar"
    }

    override fun setLayout(): Int {
        return R.layout.sysytem_progress
    }

    override fun onClick(view: View) {

    }
    private fun ss() {
        android.os.Handler().postDelayed({
            if(i>=100) {return@postDelayed}
            i+=1
            numberProgressBar.progress=i
            numberProgressBar2.progress=i
            ss();
        }, 1000)
    }
}