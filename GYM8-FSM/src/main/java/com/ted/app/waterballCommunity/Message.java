package com.ted.app.waterballCommunity;

import java.util.List;

public class Message {

    private String content;

    private List<Tag> tags;

    private Member member;

    public Message(String content, List<Tag> tags, Member member) {
        this.content = content;
        this.tags = tags;
        this.member = member;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
