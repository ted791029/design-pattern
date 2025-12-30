package com.ted.app;

import java.net.MalformedURLException;

public interface Httpclient {
    public HttpResponse sendRequest(HttpRequest request);
}
