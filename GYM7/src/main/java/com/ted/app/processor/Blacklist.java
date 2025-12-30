package com.ted.app.processor;

import com.ted.app.HostManager;
import com.ted.app.HttpProcessor;
import com.ted.app.HttpRequest;
import com.ted.app.Httpclient;

import java.util.ArrayList;
import java.util.List;

public class Blacklist extends HttpProcessor {

    public Blacklist(Httpclient next, HostManager hostManager) {
        super(next, hostManager);
    }

    @Override
    protected void handle(HttpRequest request) {
        HostManager hostManager = getHostManager();
        List<String> blacklist = hostManager.getBlacklist();

        String host = request.getOriginHost();
        if(blacklist.contains(request.getOriginHost())){
            throw new RuntimeException(host + " 在黑名單中無法連線");
        }
        System.out.println(host + "不是黑名單");
    }
}
