package com.ted.app.waterballCommunity;

public class Speak {

    private String content;

    private Member member;

    public Speak(String content, Member member) {
        this.content = content;
        this.member = member;
    }

    public String getMemberId() {
        return member.getId();
    }

    //======================================

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
