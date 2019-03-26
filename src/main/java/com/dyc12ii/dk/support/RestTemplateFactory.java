package com.dyc12ii.dk.support;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * author: dingyanchao
 * date: 2019/2/13 16:47
 * desc:
 */
public class RestTemplateFactory {

    public static RestTemplate noRedirectRest(boolean isProxy) {
        SimpleClientHttpRequestFactory requestFactory = new NoRedirectClientHttpRequestFactory();
        return getRestTemplate(isProxy, requestFactory);
    }

    public static RestTemplate normalRest() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        return getRestTemplate(false, requestFactory);
    }

    public static RestTemplate normalRest(boolean isProxy) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        return getRestTemplate(isProxy, requestFactory);
    }

    private static RestTemplate getRestTemplate(boolean isProxy, SimpleClientHttpRequestFactory requestFactory) {
        if (isProxy) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));
            requestFactory.setProxy(proxy);
        }
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }


}
