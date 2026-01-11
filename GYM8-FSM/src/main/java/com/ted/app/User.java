package com.ted.app;

public class User {

    private String id;

    private boolean isAdmin;

    private boolean isLogin = false;

    public User(String id, boolean isAdmin) {
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public void login() {
        isLogin = true;
    }

    public void logout() {
        isLogin = false;
    }

    //=============================================
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLogin() {
        return isLogin;
    }
}
