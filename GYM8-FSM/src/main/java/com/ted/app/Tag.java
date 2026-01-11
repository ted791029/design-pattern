package com.ted.app;

public class Tag {
    private String userId;

    public Tag(String userId) {
        this.userId = userId;
    }

    public String toString() {
        return "@" + userId;
    }

    //======================================

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
