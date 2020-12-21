package com.rcdz.mykotlindemo.view.activity

import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.rcdz.mykotlindemo.R
import com.wjt.mylibrary.base.BaseActivity

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/12/19 13:37
 */
class NavigationActivity : BaseActivity() {
    override fun initData() {
        val finalHost = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, finalHost)
            .setPrimaryNavigationFragment(finalHost) // 等价于 xml 中的 app:defaultNavHost="true"
            .commit()

    }
    override fun initView() {}
    override fun setNowActivityName(): String {
        return ""
    }

    override fun setLayout(): Int {
        return R.layout.layout_navigation
    }

    override fun onClick(v: View) {}
}