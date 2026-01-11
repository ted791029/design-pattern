package com.ted.app;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private List<Comment> comments = new ArrayList<>();

    private String content;

    private String id;

    private List<Tag> tags;

    private String title;

    private User user;

    public Post(String content, String id, List<Tag> tags, String title, User user) {
        this.content = content;
        this.id = id;
        this.tags = tags;
        this.title = title;
        this.user = user;
    }

    //=======================================


    public Post(List<Comment> comments, String content, String id, List<Tag> tags, String title, User user) {
        this.comments = comments;
        this.content = content;
        this.id = id;
        this.tags = tags;
        this.title = title;
        this.user = user;
    }

    //============================

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
