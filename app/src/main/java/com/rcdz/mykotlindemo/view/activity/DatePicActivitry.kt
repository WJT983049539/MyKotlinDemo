package com.rcdz.mykotlindemo.view.activity

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog
import com.rcdz.mykotlindemo.R
import com.wjt.mylibrary.base.BaseActivity
import java.text.SimpleDateFormat


/**
 * 作用:日期选择
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/12/5 14:56
 */
class DatePicActivitry : BaseActivity(){
    lateinit var showDateText:TextView
    lateinit var button11:Button
    override fun initData() {

    }

    override fun initView() {
        showDateText=fvbi(R.id.textView4)
        button11=fvbi(R.id.button11)
        button11.setOnClickListener(this)
    }

    override fun setNowActivityName(): String {
        return ""
    }

    override fun setLayout(): Int {
        return R.layout.date_pick
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.button11 -> {
                SingleDateAndTimePickerDialog.Builder(this) //.bottomSheet()
                    //.curved()
                    //.stepSizeMinutes(15)
                    //.displayHours(false)
                    //.displayMinutes(false)
                    //.todayText("aujourd'hui")
                    .displayListener(object : SingleDateAndTimePickerDialog.DisplayListener {
                        override fun onDisplayed(picker: SingleDateAndTimePicker) {
                            // Retrieve the SingleDateAndTimePicker
                            Log.i("test", "onDisplayed")

                        }

                        fun onClosed(picker: SingleDateAndTimePicker?) {
                            // On dialog closed
                            Log.i("test", "关闭了")
                        }
                    })
                    .title("Simple")
                    .listener {
                        val strDateFormat = "yyyy-MM-dd HH:mm:ss"
                        val sdf = SimpleDateFormat(strDateFormat)
                        var time=sdf.format(it)
                        Log.i("test", time)
                    }.display()
            }
        }
    }

}