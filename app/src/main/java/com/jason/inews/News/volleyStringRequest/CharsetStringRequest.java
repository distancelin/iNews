package com.jason.inews.News.volleyStringRequest;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by distancelin on 2017/3/3.
 * 继承自Volley的StringRequest，用来解决新闻详情页的返回header中没有指定编码，导致Volley采用默认编码，进而产生乱码的问题，
 * 该类指定默认编码为utf-8
 */

public class CharsetStringRequest extends StringRequest {
    public CharsetStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    private String parseCharset(Map<String, String> headers) {
        String charSet = "utf-8";
        //从header里取得Content-Type对应参数
        String contentType = headers.get("Content-Type");
        if (contentType != null) {
            //取得" charset=utf-8"字符串
            String[] contents = contentType.split(";");
            if (contents.length == 2) {
                String[] params = contents[1].trim().split("=");
                if (params[0].equals("charset")) {
                    return params[1];
                }
            }
        }
        //默认返回utf-8编码
        return charSet;
    }
}
