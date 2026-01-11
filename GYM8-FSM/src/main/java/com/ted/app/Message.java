package com.ted.app;

import java.util.List;

public class Message {

    private String content;

    private List<Tag> tags;

    private User user;

    public Message(String content, List<Tag> tags, User user) {
        this.content = content;
        this.tags = tags;
        this.user = user;
    }

    //===========================
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
