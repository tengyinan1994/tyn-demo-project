package com.example.scengine.common.utils;

import com.alibaba.fastjson.JSONObject;
import jodd.http.HttpResponse;
import lombok.extern.java.Log;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * http请求工具类
 *
 * @Author: tyn
 */
@Log
public class HttpRequestUtil {
    /**
     * 发送http请求
     *
     * @param url
     * @param headerMap       请求头
     * @param webhookBeanJson 请求体
     * @param flag            true为post请求，false为get请求
     * @return
     */
    public static Map httpRequest(String url, Map headerMap, String webhookBeanJson, boolean flag) {
        log.info("--------------HttpRequest begin---------------------");
        log.info("url:" + url);
        Map header;
        if (StringUtils.isEmpty(headerMap)) {
            header = new HashMap(16);
            header.put("Accept", "application/json");
        } else {
            header = headerMap;
        }
        log.info("headerMap:" + header);
        HttpResponse res;
        if (flag) {
            log.info("body:" + webhookBeanJson);
            res = jodd.http.HttpRequest
                    .post(url)
                    .header(header)
                    .timeout(7000)
                    .connectionTimeout(7000)
                    .bodyText(webhookBeanJson, "application/json", "UTF-8")
                    .send().close();//执行
        } else {
            res = jodd.http.HttpRequest
                    .get(url)
                    .header(header)
                    .timeout(7000)
                    .connectionTimeout(7000)
                    .send().close();//执行
        }


        String jsonString = res.charset("UTF-8").bodyText();
        log.info("--------------jsonString---------------------" + jsonString);
        Map responseMap = (Map) JSONObject.parse(res.charset("UTF-8").bodyText());
        return responseMap;

    }

}
