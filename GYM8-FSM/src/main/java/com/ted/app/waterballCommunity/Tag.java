package com.ted.app.waterballCommunity;

public class Tag {
    private String memberId;

    public Tag(String memberId) {
        this.memberId = memberId;
    }

    public String toString() {
        return "@" + memberId;
    }

    //======================================

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
