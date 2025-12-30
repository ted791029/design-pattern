package com.ted.app;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class HttpRequest {

    private String originHost;

    private List<String> processedHosts;

    private URL url;


    public HttpRequest(String url) throws MalformedURLException {
        this.url = new URL(url);
        originHost = this.url.getHost();
        processedHosts = List.of(originHost);
    }

    public String getHost(){
        return url.getHost();
    }

    //===============================
    public String getOriginHost() {
        return originHost;
    }

    public void setOriginHost(String originHost) {
        this.originHost = originHost;
    }

    public List<String> getProcessedHosts() {
        return processedHosts;
    }

    public void setProcessedHosts(List<String> processedHosts) {
        this.processedHosts = processedHosts;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
