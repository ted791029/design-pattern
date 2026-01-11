package com.ted.app;

public class Speak {

    private String content;

    private User user;

    public Speak(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public String getUserId() {
        return user.getId();
    }

    //======================================

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
