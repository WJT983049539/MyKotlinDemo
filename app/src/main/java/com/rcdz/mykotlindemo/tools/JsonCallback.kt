package com.rcdz.mykotlindemo.tools

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import com.lzy.okgo.callback.AbsCallback
import okhttp3.Response
import java.lang.reflect.ParameterizedType

abstract class JsonCallback<T> : AbsCallback<T?>() {
    @Throws(Throwable::class)
    override fun convertResponse(response: Response): T? {
        val code = response.code()
        Log.i("test", code.toString())
        val body = response.body() ?: return null
        var data: T? = null
        val gson =GsonBuilder().registerTypeAdapterFactory(NullStringEmptyTypeAdapterFactory<Any?>())
                .create()
        val jsonReader = JsonReader(body.charStream())
        val genType = javaClass.genericSuperclass
        val type = (genType as ParameterizedType?)!!.actualTypeArguments[0]
        data = gson.fromJson(jsonReader, type)
        return data
    }

    override fun onError(response: com.lzy.okgo.model.Response<T?>) {
        super.onError(response)
        val exception = response.exception
        exception?.printStackTrace()
        //        if(exception instanceof UnknownHostException ||exception instanceof ConnectException){
//            GlobalToast.show("网络连接失败，请连接网络！", Toast.LENGTH_LONG);
//            if(AppManager.getAppManager().currentActivity() instanceof DisconnectActivity){
//            }else {
//                OpenHelper.openDisconnectActivity(AppManager.getAppManager().currentActivity());
//            }
//        }else if(exception instanceof SocketTimeoutException){
//            GlobalToast.show("网络请求超时", Toast.LENGTH_LONG);
//            if(AppManager.getAppManager().currentActivity()instanceof DisconnectActivity){
//            }else {
//                OpenHelper.openDisconnectActivity(AppManager.getAppManager().currentActivity());
//            }
//        }else if(exception instanceof HttpException){
//            GlobalToast.show("服务端异常了", Toast.LENGTH_LONG);
//            if(AppManager.getAppManager().currentActivity()instanceof DisconnectActivity){
//            }else {
//                OpenHelper.openDisconnectActivity(AppManager.getAppManager().currentActivity());
//            }
//        }else if(exception instanceof StorageException){
//            GlobalToast.show("服务端异常了", Toast.LENGTH_LONG);
//        }else if(exception instanceof IllegalStateException){
//            GlobalToast.show(exception.getMessage(), Toast.LENGTH_LONG);
//        }
    }
}