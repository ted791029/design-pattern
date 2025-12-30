package com.ted.app;

import com.ted.app.client.FakeHttpClient;
import com.ted.app.processor.Blacklist;
import com.ted.app.processor.LoadBalancing;
import com.ted.app.processor.ServiceDiscovery;

import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        String configPath = "D:\\sourcetree\\design-pattern\\GYM7\\src\\main\\resources\\host.conf";
        String blacklistConfigPath = "D:\\sourcetree\\design-pattern\\GYM7\\src\\main\\resources\\blacklist.conf";
        HttpClientConfigurator httpClientConfigurator = new HttpClientConfigurator(configPath, blacklistConfigPath);
        HostManager hostManager = new HostManager(httpClientConfigurator);

        Httpclient httpclient = new ServiceDiscovery(
                new LoadBalancing(
                        new Blacklist(
                             new FakeHttpClient(), hostManager
                        ), hostManager
                ), hostManager
        );

        //正常
        String url = "http://example32.com/world";
        httpclient.sendRequest(new HttpRequest(url));

//        //黑名單
//        url = "http://example03.com/world";
//        httpclient.sendRequest(new HttpRequest(url));

        Httpclient httpclient2 = new Blacklist(
                new LoadBalancing(
                        new ServiceDiscovery(
                                new FakeHttpClient(), hostManager
                        ), hostManager
                ), hostManager
        );
        httpclient2.sendRequest(new HttpRequest(url));
    }
}
