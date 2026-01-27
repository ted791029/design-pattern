package com.ted.app.waterballCommunity;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private List<Comment> comments = new ArrayList<>();

    private String content;

    private String id;

    private List<Tag> tags;

    private String title;

    private Member member;

    public Post(String content, String id, List<Tag> tags, String title, Member member) {
        this.content = content;
        this.id = id;
        this.tags = tags;
        this.title = title;
        this.member = member;
    }

    //=======================================


    public Post(List<Comment> comments, String content, String id, List<Tag> tags, String title, Member member) {
        this.comments = comments;
        this.content = content;
        this.id = id;
        this.tags = tags;
        this.title = title;
        this.member = member;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
