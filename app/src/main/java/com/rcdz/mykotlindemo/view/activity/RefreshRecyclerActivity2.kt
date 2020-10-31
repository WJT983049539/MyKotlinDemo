package com.rcdz.mykotlindemo.view.activity

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.rcdz.mykotlindemo.R
import com.rcdz.mykotlindemo.model.adapter.RecyclerViewAdapter
import com.rcdz.mykotlindemo.model.bean.UserDataLoadMore
import com.wjt.mylibrary.base.BaseRefreshListener
import kotlinx.android.synthetic.main.activity_refresh.*

/**
 * 上拉刷新 下拉加载
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
class RefreshRecyclerActivity2 : com.wjt.mylibrary.base.BaseActivity(){
    var list=ArrayList<UserDataLoadMore>()
    lateinit var adapter: RecyclerViewAdapter
    override fun initView() {
        //设置线性布局管理器
        rc_test.layoutManager = LinearLayoutManager(this)
        adapter=RecyclerViewAdapter(list)
        rc_test.adapter
    }
    override fun initData() {
        for(i in 0..10){
            var bean=UserDataLoadMore("测试1",i)
            list.add(bean)
        }
    }

    override fun setNowActivityName(): String {
       return "as"
    }

    override fun setLayout(): Int {
        return R.layout.activity_refresh
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
        }
    }
}