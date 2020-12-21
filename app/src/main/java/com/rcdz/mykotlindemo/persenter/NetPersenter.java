package com.rcdz.mykotlindemo.persenter;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.rcdz.mykotlindemo.model.bean.NewsBean;
import com.rcdz.mykotlindemo.tools.JsonCallback;

/**
 * @author:create by
 * 邮箱 983049539@qq.com
 */
public class NetPersenter {
    private Context context;

    public NetPersenter(Context context) {
        this.context = context;
    }

    /**
     * 请求新闻数据
     */
    public void getNewsInfo(final GetNewsInfo getNewsInfo) {
        String url = "https://news-at.zhihu.com/api/4/news/latest";
        OkGo.<NewsBean>get(url).execute(new JsonCallback<NewsBean>() {
            @Override
            public void onSuccess(Response<NewsBean> response) {
                Log.i("test",response.body().toString());
                NewsBean newsBean=response.body();
                if(getNewsInfo!=null){ //这里是必须的

                    getNewsInfo.getNewsInfo(newsBean);
                }
            }

            @Override
            public void onError(Response<NewsBean> response) {
                super.onError(response);
                Log.i("test",response.getException().getMessage());
            }
        });
    }
}
