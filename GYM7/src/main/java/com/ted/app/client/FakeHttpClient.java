package com.ted.app.client;

import com.ted.app.HttpRequest;
import com.ted.app.HttpResponse;
import com.ted.app.Httpclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class FakeHttpClient implements Httpclient {
    @Override
    public HttpResponse sendRequest(HttpRequest request){
        //預設取第一筆
        List<String> processedHosts = request.getProcessedHosts();
        String host = processedHosts.get(0);

        try {
            //替換處理過的host
            updateHost(request, host);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        //假的回應
        return getResponse();
    }

    private HttpResponse getResponse(){
        Random rand = new Random();
        // 產生 0 到 1 的隨機整數
        int randomInt = rand.nextInt(2);

        if(randomInt == 0){
            System.out.println("Response為: 200");
            return new HttpResponse(200);
        }
        System.out.println("Response為: 400");
        return new HttpResponse(400);
    }

    private void updateHost(HttpRequest request, String newHost) throws MalformedURLException {
        URL oldUrl = request.getUrl();
        URL url = new URL(oldUrl.getProtocol(),
                newHost,
                oldUrl.getPort(),
                oldUrl.getFile());
        request.setUrl(url);
    }
}
