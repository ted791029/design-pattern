package com.ted.app;

import java.util.List;
import java.util.Map;

public class HostManager {

    private List<String> blacklist;

    private Map<String, List<IP>> hostConfigMap;

    private HttpClientConfigurator httpClientConfigurator;

    public HostManager(HttpClientConfigurator httpClientConfigurator) {
        this.httpClientConfigurator = httpClientConfigurator;
        this.hostConfigMap = httpClientConfigurator.loadConfig();
        this.blacklist = httpClientConfigurator.loadBlacklistConfig();
    }

    public List<IP> getIpList(String host){
        return hostConfigMap.get(host);
    }

    //===============================


    public List<String> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(List<String> blacklist) {
        this.blacklist = blacklist;
    }

    public Map<String, List<IP>> getHostConfigMap() {
        return hostConfigMap;
    }

    public void setHostConfigMap(Map<String, List<IP>> hostConfigMap) {
        this.hostConfigMap = hostConfigMap;
    }

    public HttpClientConfigurator getHttpClientConfigurator() {
        return httpClientConfigurator;
    }

    public void setHttpClientConfigurator(HttpClientConfigurator httpClientConfigurator) {
        this.httpClientConfigurator = httpClientConfigurator;
    }
}
