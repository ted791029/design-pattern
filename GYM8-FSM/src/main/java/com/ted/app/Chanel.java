package com.ted.app;

public class Chanel {

    public boolean isUserNotLogin(User user) {
        return user != null && !user.isLogin();
    }

}
