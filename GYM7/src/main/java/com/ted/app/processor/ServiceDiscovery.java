package com.ted.app.processor;

import com.ted.app.*;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceDiscovery extends HttpProcessor {


    public ServiceDiscovery(Httpclient next, HostManager hostManager) {
        super(next, hostManager);
    }

    @Override
    protected void handle(HttpRequest request) {
        String host = request.getHost();
        List<IP> ipList = getHostConfig(request.getHost());

        if(ipList == null){
            throw new RuntimeException("查無此Host: " + host);
        }

        List<String> processedHosts = ipList.stream()
                .filter(IP::checkStatus)
                .map(ip -> ip.getAddress())
                .collect(Collectors.toList());

        request.setProcessedHosts(processedHosts);
        System.out.println("經過Service Discovery，發現有效host為: " + processedHosts);
    }

    @Override
    protected void postSendRequest(String originHost, String newHost, HttpResponse response){
        //經過ServiceDiscovery Host 會從名稱形式轉為IP形式
        List<IP> ipList = getHostConfig(originHost);

        if(ipList == null){
            throw new RuntimeException("查無此Host " + originHost);
        }

        IP ip = ipList.stream()
                .filter(p -> newHost.equals(p.getAddress()))
                .findFirst() // 找出第一個符合條件的元素
                .orElse(null);

        if(ip == null){
            throw new RuntimeException("查無此IP: " + newHost);
        }

        if(response.getStatus() == 200){
            ip.setEnable(true);
            ip.setDisableTime(null);
        }else {
            ip.setEnable(false);
            ip.setDisableTime(System.currentTimeMillis());
        }
    }
}
