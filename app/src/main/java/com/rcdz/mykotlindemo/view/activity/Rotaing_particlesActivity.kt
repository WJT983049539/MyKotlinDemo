package com.rcdz.mykotlindemo.view.activity

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.rcdz.mykotlindemo.R
import com.rcdz.mykotlindemo.tools.GlideApp
import com.wjt.mylibrary.base.BaseActivity
import kotlinx.android.synthetic.main.activity_roating.*


/**
 * 旋转粒子效果
 */
class Rotaing_particlesActivity :BaseActivity(){
    override fun initData() {
    }

    override fun initView() {
//        GlideApp.with(this)
//            .load(R.mipmap.ic_launcher)
//            .circleCrop()
//            .into(music_avatar)
//
//
//        music_avatar.setOnClickListener(this)
    }

    override fun setNowActivityName(): String {
        return "旋转粒子"
    }

    override fun setLayout(): Int {
        return R.layout.activity_roating
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
//            R.id.music_avatar -> {
//
//                val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
//                music_avatar.startAnimation(animation)
//            }
        }
    }

}
