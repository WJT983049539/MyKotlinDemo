package com.rcdz.mykotlindemo

import com.rcdz.mykotlindemo.tools.SensitiveWordUtils
import com.rcdz.mykotlindemo.tools.SensitivewordFilter
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.ArrayList

/**
 * Kotlin 基础练习
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

//        $ 表示一个变量名或者变量值
//        $varName 表示变量值
//        ${varName.fun()} 表示变量的方法返回值:
        var s1= "a==$a"
        print(s1)
        val list: MutableList<String> = ArrayList()
        list.add("wo")
        list.add("cao")
        //从文件中读取词库中的内容，将内容添加到list集合中
        //初始化词库,读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：
        //从文件中读取词库中的内容，将内容添加到list集合中
        //初始化词库,读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：
        SensitiveWordUtils.init(list)
        //输入的字符串
        //List<String> rep = SensitivewordFilter.getSensitiveWord(datas,str,1);
        //输入的字符串
        //List<String> rep = SensitivewordFilter.getSensitiveWord(datas,str,1);
        val six = SensitivewordFilter.replaceSensitiveWord(list, "我是不会放弃的，我wo,cao,用不放弃", 1, "*")
        print(six)
    }
}
