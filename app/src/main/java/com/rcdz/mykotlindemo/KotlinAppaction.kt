package com.rcdz.mykotlindemo

import android.app.Application
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.https.HttpsUtils
import com.qw.soul.permission.SoulPermission
import me.jessyan.autosize.AutoSize
import me.jessyan.autosize.AutoSizeConfig
import okhttp3.OkHttpClient
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
 * @author:create by
 * 邮箱 983049539@qq.com
 */
class KotlinAppaction : Application() {
    override fun onCreate() {
        super.onCreate()
        AutoSizeConfig.getInstance().setCustomFragment(true);//适配fragment
        AutoSize.initCompatMultiProcess(this);
        val sslParams = HttpsUtils.getSslSocketFactory()
        val builder = OkHttpClient.Builder()  //设置忽略https证书
            .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
            .hostnameVerifier(object : HostnameVerifier {
                override fun verify(hostname: String?, session: SSLSession?): Boolean {
                    return true
                }
            }).build()
        OkGo.getInstance().init(this@KotlinAppaction) //必须调用初始化
            .setOkHttpClient(builder) //建议设置OkHttpClient，不设置将使用默认的
            .setCacheMode(CacheMode.NO_CACHE) //全局统一缓存模式，默认不使用缓存，可以不传
            .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE).retryCount = 3 //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
        OkGo.getInstance().okHttpClient = builder
        SoulPermission.init(this) //初始化权限请求
    }









}