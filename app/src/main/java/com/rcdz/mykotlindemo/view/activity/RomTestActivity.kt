package com.rcdz.mykotlindemo.view.activity

import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.rcdz.mykotlindemo.R

/**
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
class RomTestActivity : com.wjt.mylibrary.base.BaseActivity() {
    lateinit var delImageView: ImageView

    override fun initData() {
    }

    override fun initView() {
        delImageView = findViewById(R.id.dice_image)
    val rollbutton:Button=findViewById(R.id.roll_button) //找到按钮
        rollbutton.setOnClickListener { rollDice() }
    }

    private fun rollDice() {
        val randomInt = (1..6).random()
        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        delImageView.setImageResource(drawableResource)
    }

    override fun setNowActivityName(): String {
        return "丢色子测试"
    }

    override fun setLayout(): Int {
        return R.layout.layout_activity_rodom
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}