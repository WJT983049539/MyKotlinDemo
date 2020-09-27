package com.rcdz.mykotlindemo

import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.rcdz.mykotlindemo.designtest.DrawerLayoutActivity
import com.wjt.mylibrary.base.BaseActivity
import com.rcdz.mykotlindemo.view.activity.RefreshRecyclerActivity
import com.rcdz.mykotlindemo.view.activity.Rotaing_particlesActivity

class MainActivity : com.wjt.mylibrary.base.BaseActivity(), View.OnClickListener{
    lateinit var bt_ry_refresh:Button
    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun initData() {

    }

    override fun initView() {
        val designbutton: Button =findViewById(R.id.dssign)
        designbutton.setOnClickListener(this) //设置点击事件
        bt_ry_refresh=findViewById(R.id.bt_ry_refresh)
        bt_ry_refresh.setOnClickListener(this)
        val bt_rotating_particles:Button=findViewById(R.id.bt_rotating_particles)
        bt_rotating_particles.setOnClickListener(this)
    }

    override fun setNowActivityName(): String {
        return "MainActivity"
    }

    override fun onClick(p0: View?) {

        when(p0?.id){
            //点击进入designActivity
            R.id.dssign->{
                val intent=Intent(this@MainActivity,DrawerLayoutActivity::class.java)
                    startActivity(intent)
                this@MainActivity.finish();

            }
            R.id.bt_ry_refresh->{ //上拉刷新 下拉加载
                var intent=Intent(this@MainActivity, RefreshRecyclerActivity::class.java)
                startActivity(intent)
                this@MainActivity.finish();
            }
            R.id.bt_rotating_particles->{
                var intent=Intent(this@MainActivity, Rotaing_particlesActivity::class.java)
                startActivity(intent)
            }

        }


    }

}
