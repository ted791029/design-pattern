package com.ted.app.processor;

import com.ted.app.HostManager;
import com.ted.app.HttpProcessor;
import com.ted.app.HttpRequest;
import com.ted.app.Httpclient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadBalancing extends HttpProcessor {

    private Map<String, Integer> lastIndexMap;
    public LoadBalancing(Httpclient next, HostManager hostManager) {
        super(next, hostManager);
        lastIndexMap = new HashMap<>();
    }

    @Override
    protected void handle(HttpRequest request) {
        String key = request.getOriginHost();
        List<String> processedHosts = request.getProcessedHosts();
        int lastIndex = lastIndexMap.getOrDefault(key, -1);
        int index = lastIndex + 1;
        index = index >= processedHosts.size() ? (index % processedHosts.size()) : index;
        String host = processedHosts.get(index);
        request.setProcessedHosts(List.of(host));
        lastIndexMap.put(key, index);
        System.out.println("經過Load Balancing，選擇的host: " + host);
    }

    //===============================

    public Map<String, Integer> getLastIndexMap() {
        return lastIndexMap;
    }

    public void setLastIndexMap(Map<String, Integer> lastIndexMap) {
        this.lastIndexMap = lastIndexMap;
    }
}
