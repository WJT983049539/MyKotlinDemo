package com.wjt.mylibrary.widges.refreshview

import android.view.View

/**
 * 当前类注释:
 * 作者：jinwenfeng on 2016/12/6 13:48
 * 邮箱：823546371@qq.com
 * QQ： 823546371
 * 公司：南京穆尊信息科技有限公司
 * © 2016 jinwenfeng
 * © 版权所有，未经允许不得传播
 */
interface HeadView {
    /**
     * 开始下拉
     */
    fun begin()

    /**
     * 回调的精度,单位为px
     *
     * @param progress 当前高度
     * @param all      总高度
     */
    fun progress(progress: Float, all: Float)
    fun finishing(progress: Float, all: Float)

    /**
     * 下拉完毕
     */
    fun loading()

    /**
     * 看不见的状态
     */
    fun normal()

    /**
     * 返回当前视图
     */
    val view: View?
}