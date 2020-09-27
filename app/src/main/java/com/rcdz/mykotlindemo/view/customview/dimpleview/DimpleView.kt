package com.rcdz.mykotlindemo.view.customview.dimpleview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import java.lang.Math.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

/**
 * 作用:
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/9/22 14:21
 */
class DimpleView (context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var animator = ValueAnimator.ofFloat(0f, 1f)
    private val pathMeasure = PathMeasure()//路径，用于测量扩散圆某一处的X,Y值
    private var pos = FloatArray(2) //扩散圆上某一点的x,y
    private val tan = FloatArray(2)//扩散圆上某一点切线
    //定义一个粒子的集合
     var particleList = mutableListOf<Particle>()
    var centerX:Float = 0.0f
    var centerY:Float=0.0f

    //定义画笔
    var paint = Paint()
    var path = Path()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.WHITE
        paint.isAntiAlias = true
        var time= measureTimeMillis {
            particleList.forEach {
                paint.alpha=it.alpha
//                canvas.drawPath(path,paint)
                canvas.drawCircle(it.x,it.y,it.radius,paint)
            }
        }
        Log.i("dimple","绘制时间$time ms")
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        var angle=0.0
        centerX= (w/2).toFloat()
        centerY= (h/2).toFloat()
        path.addCircle(centerX, centerY, 280f, Path.Direction.CCW)
        pathMeasure.setPath(path, false) //添加path

        var nextX=0f
        var nextY=0f
        var speed=0
          for (i in 0..2000){
              //按比例测量路径上每一点的值
              pathMeasure.getPosTan(i / 2000f * pathMeasure.length, pos, tan)
              //反余弦函数可以得到角度值，是弧度
              angle=acos(((pos[0] - centerX) / 280f).toDouble())
              nextX = pos[0]+Random.nextInt(6) - 3f //X值随机偏移
              nextY=  pos[1]+Random.nextInt(6) - 3f//Y值随机偏移
            speed= Random.nextInt(10)+5    //速度从5-15不等
            particleList.add(Particle(nextX,nextY,2f,speed.toFloat(),100,300f, 0,angle))
    }
        animator.start()//别忘了启动动画
    }

    init {
        animator.duration = 2000
        animator.repeatCount = -1
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            updateParticle(it.animatedValue as Float)
            invalidate()//重绘界面
        }
    }
    //更新速度
    private fun updateParticle(value: Float) {
        particleList.forEach {particle->
            if(particle.offset >particle.maxOffset){
                particle.offset=0
                particle.speed= (Random.nextInt(10)+5).toFloat()
            }
            particle.alpha= ((1f - particle.offset / particle.maxOffset)  * 225f).toInt()
            particle.x = (centerX+ cos(particle.angle) * (280f + particle.offset)).toFloat()
            if (particle.y > centerY) {
                particle.y = (sin(particle.angle) * (280f + particle.offset) + centerY).toFloat()
            } else {
                particle.y = (centerY - sin(particle.angle) * (280f + particle.offset)).toFloat()
            }
            particle.offset += particle.speed.toInt()
        }
    }
}