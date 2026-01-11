package com.ted.app;

import com.google.gson.JsonObject;
import com.ted.app.chanel.Broadcast;
import com.ted.bot.BotEvent;
import com.ted.bot.BotEventName;
import com.ted.bot.Community;
import com.ted.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WaterballCommunity implements Community{

    private Map<String , Chanel> chanelMap;

    private EventManager manager;
    private Map<String, User> onlineUsers;

    @Override
    public List<String> allOnlineUserId() {

        return new ArrayList<>(onlineUsers.keySet());
    }

    @Override
    public boolean isBroadcasting() {
        Broadcast broadcast = (Broadcast) chanelMap.get("Broadcast");
        return broadcast.isBroadcasting();
    }

    @Override
    public void botGoBroadcasting(){
        Broadcast broadcast = (Broadcast) chanelMap.get("Broadcast");
        broadcast.setBroadcasting(true);
    }

    @Override
    public void botStopBroadcasting(){
        Broadcast broadcast = (Broadcast) chanelMap.get("Broadcast");
        broadcast.setBroadcasting(false);
    }


    public void login(User user) throws InterruptedException {
        onlineUsers.put(user.getId(), user);
        user.login();
        JsonObject payload = new JsonObject();
        payload.addProperty("user", JsonUtil.toJson(user));
        EventManager.submit(new BotEvent(BotEventName.LOGIN.getName(), JsonUtil.toJson(payload)));
    }

    public void logout(User user) throws InterruptedException {
        onlineUsers.remove(user.getId());
        user.logout();
        JsonObject payload = new JsonObject();
        payload.addProperty("user", JsonUtil.toJson(user));
        EventManager.submit(new BotEvent(BotEventName.LOGOUT.getName(), JsonUtil.toJson(payload)));
    }

    @Override
    public int totalOnlineUser() {
        return onlineUsers.size();
    }

    //=================================


    public Map<String, Chanel> getChanelMap() {
        return chanelMap;
    }

    public void setChanelMap(Map<String, Chanel> chanelMap) {
        this.chanelMap = chanelMap;
    }

    public EventManager getManager() {
        return manager;
    }

    public void setManager(EventManager manager) {
        this.manager = manager;
    }

    public Map<String, User> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(Map<String, User> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }
}
