package com.ted.app;

import java.util.List;

public class Comment {
    private String content;

    private String postId;

    private List<Tag> tags;

    private User user;

    public Comment(String content, String postId, List<Tag> tags, User user) {
        this.content = content;
        this.postId = postId;
        this.tags = tags;
        this.user = user;
    }

    //===============================
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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
