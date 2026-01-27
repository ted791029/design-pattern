package com.ted.app.waterballCommunity;

import java.util.List;

public class Comment {
    private String content;

    private String postId;

    private List<Tag> tags;

    private Member member;

    public Comment(String content, String postId, List<Tag> tags, Member member) {
        this.content = content;
        this.postId = postId;
        this.tags = tags;
        this.member = member;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
