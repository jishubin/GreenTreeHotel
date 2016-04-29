package com.jerry.greentreehotel.utils;

import android.util.Log;

import com.jerry.greentreehotel.base.EventMessage;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;
import java.util.Set;

import de.greenrobot.event.EventBus;

/**
 * Created by mac on 27/4/16.
 */
public class HttpUtil
{
    private static HttpUtil httpUtil;

    private HttpUtil()
    {

    }

    public static HttpUtil getInstance()
    {
        if (httpUtil == null)
        {
            synchronized (HttpUtil.class)
            {
                httpUtil = new HttpUtil();
            }
        }

        return httpUtil;
    }

    /**
     * 发送Post请求
     */
    public void sendPost(final String url, Map<String, Object> params)
    {
        RequestParams requestParams = new RequestParams(url);

        if (params != null)
        {
            Set<Map.Entry<String, Object>> set = params.entrySet();
            for (Map.Entry<String, Object> entry : set)
            {
                requestParams.addBodyParameter(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        x.http().post(requestParams, new Callback.CommonCallback<String>()
        {
            @Override
            public void onSuccess(String result)
            {
                Log.d("success", "onSuccess: "+result);
                // 发送结果给前面Activity
                EventBus.getDefault().post(new EventMessage(url,result));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback)
            {
                Log.d("error", "onError() ========== " + ex);
            }

            @Override
            public void onCancelled(CancelledException cex)
            {
                Log.d("oncancelled", "onCancelled: "+cex);
            }

            @Override
            public void onFinished()
            {
                Log.d("onfinished", "onFinished: "+"sss");

            }
        });
    }
}
