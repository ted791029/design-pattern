package com.ted.app.waterballCommunity;

public class Member {

    private String id;

    private boolean isAdmin;

    private boolean isLogin = false;

    public Member(String id, boolean isAdmin) {
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
