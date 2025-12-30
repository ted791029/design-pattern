package com.ted.app;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class HttpProcessor implements Httpclient{

    private Httpclient next;

    private HostManager hostManager;

    public HttpProcessor(Httpclient next, HostManager hostManager) {
        this.next = next;
        this.hostManager = hostManager;
    }

    public HttpResponse sendRequest(HttpRequest request){
        handle(request);
        HttpResponse response = next.sendRequest(request);
        postSendRequest(request.getOriginHost(), request.getHost(), response);
        return response;
    }

    protected List<IP> getHostConfig(String host){
        return hostManager.getIpList(host);
    }

    protected abstract void handle(HttpRequest request);

    protected void postSendRequest(String oldHost, String newHost, HttpResponse response){

    }

    //===============================


    public Httpclient getNext() {
        return next;
    }

    public void setNext(Httpclient next) {
        this.next = next;
    }

    public HostManager getHostManager() {
        return hostManager;
    }

    public void setHostManager(HostManager hostManager) {
        this.hostManager = hostManager;
    }
}
