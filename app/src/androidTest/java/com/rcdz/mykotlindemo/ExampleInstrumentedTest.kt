package com.rcdz.mykotlindemo

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.rcdz.mykotlindemo.tools.SensitiveWordUtils
import com.rcdz.mykotlindemo.tools.SensitivewordFilter
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.rcdz.mykotlindemo", appContext.packageName)
        System.out.println("wokao ")

        //从文件中读取词库中的内容，将内容添加到list集合中
        //初始化词库,读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：




    }
}
