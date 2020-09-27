package com.wjt.mylibrary.widges.refreshview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.wjt.mylibrary.R

/**
 * 当前类注释:
 * 作者：jinwenfeng on 2016/12/8 10:47
 * 邮箱：823546371@qq.com
 * QQ： 823546371
 * 公司：南京穆尊信息科技有限公司
 * © 2016 jinwenfeng
 * © 版权所有，未经允许不得传播
 */
class LoadMoreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), FooterView {
    private var tv: TextView? = null
    private var arrow: ImageView? = null
    private var progressBar: ProgressBar? = null
    private fun init(context: Context) {
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_header, null)
        addView(view)
        tv = view.findViewById<View>(R.id.header_tv) as TextView
        arrow = view.findViewById<View>(R.id.header_arrow) as ImageView
        progressBar = view.findViewById<View>(R.id.header_progress) as ProgressBar
    }

    override fun begin() {}
    override fun progress(progress: Float, all: Float) {
        val s = progress / all
        if (s >= 0.9f) {
            arrow!!.rotation = 0f
        } else {
            arrow!!.rotation = 180f
        }
        if (progress >= all - 10) {
            tv!!.text = "松开加载更多"
        } else {
            tv!!.text = "上拉加载"
        }
    }

    override fun finishing(progress: Float, all: Float) {}
    override fun loading() {
        arrow!!.visibility = GONE
        progressBar!!.visibility = VISIBLE
        tv!!.text = "加载中..."
    }

    override fun normal() {
        arrow!!.visibility = VISIBLE
        progressBar!!.visibility = GONE
        tv!!.text = "上拉加载"
    }

    override val view: View
        get() = this

    init {
        init(context)
    }
}