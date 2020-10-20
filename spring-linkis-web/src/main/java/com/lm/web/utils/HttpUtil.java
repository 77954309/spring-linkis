package com.lm.web.utils;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname HttpUtil
 * @Description TODO
 * @Date 2020/10/20 14:19
 * @Created by limeng
 */
public class HttpUtil {
    public static RestTemplate getRestClient(){
        CloseableHttpClient build =  HttpClientBuilder.create().useSystemProperties().build();
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(build));
    }


}
