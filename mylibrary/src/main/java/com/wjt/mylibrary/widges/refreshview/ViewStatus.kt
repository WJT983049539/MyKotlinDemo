package com.wjt.mylibrary.widges.refreshview

import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 *
 */
object ViewStatus {
    const val CONTENT_STATUS = 0 //内容页面
    const val LOADING_STATUS = 1 //加载页面
    const val EMPTY_STATUS = 2  //空数据页面
    const val ERROR_STATUS = 3  //没有网络错误页面

    @IntDef(CONTENT_STATUS, LOADING_STATUS, EMPTY_STATUS, ERROR_STATUS)
    @Retention(RetentionPolicy.SOURCE)
    annotation class VIEW_STATUS
}