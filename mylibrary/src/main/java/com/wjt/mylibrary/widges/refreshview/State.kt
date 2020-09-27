package com.wjt.mylibrary.widges.refreshview

import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by jiang on 16/8/17.
 */
object State {
    const val REFRESH = 10
    const val LOADMORE = 11

    @IntDef(REFRESH, LOADMORE)
    @Retention(RetentionPolicy.SOURCE)
    annotation class REFRESH_STATE
}