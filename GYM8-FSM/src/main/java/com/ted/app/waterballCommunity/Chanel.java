package com.ted.app.waterballCommunity;

public class Chanel {

    public boolean isMemberNotLogin(Member member) {
        return member != null && !member.isLogin();
    }

}
