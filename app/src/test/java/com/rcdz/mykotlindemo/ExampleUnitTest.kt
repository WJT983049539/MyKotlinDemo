package com.rcdz.mykotlindemo

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2) //通过往下走
        System.out.println("wokao ")
        var a=18
        var b=129
        println("a 和b 与的结果是：" + (a and b))
        val aq = 2
        println("a 非的结果是：" + aq.inv())
    }
}
