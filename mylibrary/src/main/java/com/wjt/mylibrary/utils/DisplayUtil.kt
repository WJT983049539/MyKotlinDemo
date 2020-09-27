package com.wjt.mylibrary.utils

import android.content.Context

/**
 * 当前类注释:
 * 作者：jinwenfeng on 2016/12/13 16:06
 * 邮箱：823546371@qq.com
 * QQ： 823546371
 * 公司：南京穆尊信息科技有限公司
 * © 2016 jinwenfeng
 * © 版权所有，未经允许不得传播
 */
object DisplayUtil {
    fun dp2Px(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    fun px2Dp(context: Context, px: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }
    fun dp2Px(context: Context, dp: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    fun px2Dp(context: Context, px: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    fun sp2px(context: Context, spValue: Int): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }
}