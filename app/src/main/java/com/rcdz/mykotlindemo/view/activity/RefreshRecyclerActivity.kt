package com.rcdz.mykotlindemo.view.activity

import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.rcdz.mykotlindemo.R
import com.rcdz.mykotlindemo.model.adapter.RecyclerViewAdapter
import com.rcdz.mykotlindemo.model.bean.UserDataLoadMore
import com.wjt.mylibrary.base.BaseRefreshListener
import com.wjt.mylibrary.widges.refreshview.ViewStatus
import kotlinx.android.synthetic.main.activity_refresh.*

/**
 * 上拉刷新 下拉加载
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
class RefreshRecyclerActivity : com.wjt.mylibrary.base.BaseActivity(){
    var list=ArrayList<UserDataLoadMore>()
    lateinit var adapter1: RecyclerViewAdapter
    override fun initView() {
        //设置线性布局管理器

        Handler().postDelayed({
            rc_refresh.showView(ViewStatus.LOADING_STATUS)
            Handler().postDelayed({
                rc_refresh.showView(ViewStatus.EMPTY_STATUS)
                Handler().postDelayed({
                    rc_refresh.showView(ViewStatus.ERROR_STATUS)
                    initError()
                }, 2000)
            }, 2000)
        }, 2000)

        rc_test.layoutManager = LinearLayoutManager(this)
        adapter1=RecyclerViewAdapter(list)
        rc_test.adapter=adapter1

//        rc_refresh.autoRefresh() //自动刷新

        rc_refresh.setRefreshListener(object : BaseRefreshListener {
            override fun refresh() {  //下拉刷新
                Handler().postDelayed({ rc_refresh.finishRefresh() }, 2000)
            }

            override fun loadMore() { //下拉加载
                var bean = UserDataLoadMore("刷新新加数据", 0)
                list.add(bean)
                adapter1.notifyDataSetChanged()
                Handler().postDelayed({ rc_refresh.finishLoadMore() }, 2000)
            }

        })
    }
    override fun initData() {
        for(i in 0..10){
            var bean=UserDataLoadMore("测试" + i, i)
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

    private fun initError() {
        // 获取页面
        val error: View = rc_refresh.getView(ViewStatus.ERROR_STATUS)!!
        error.findViewById<View>(R.id.error_refresh).setOnClickListener {
            rc_refresh.showView(ViewStatus.LOADING_STATUS)
            Handler()
                .postDelayed({ rc_refresh.showView(ViewStatus.CONTENT_STATUS) }, 2000)
        }
    }
}