package com.ted.app;

public class HttpResponse {

    private int status;

    public HttpResponse(int status) {
        this.status = status;
    }

    //===============================

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
