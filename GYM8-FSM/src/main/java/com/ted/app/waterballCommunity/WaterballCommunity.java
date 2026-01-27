package com.ted.app.waterballCommunity;

import com.google.gson.JsonObject;
import com.ted.app.waterballCommunity.chanel.Broadcast;
import com.ted.bot.BotEvent;
import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.Community;
import com.ted.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WaterballCommunity implements Community{

    private Map<String, Chanel> chanelMap;

    private EventManager manager;
    private Map<String, Member> onlineMembers;

    @Override
    public List<String> allOnlineMemberId() {

        return new ArrayList<>(onlineMembers.keySet());
    }

    @Override
    public boolean isBroadcasting() {
        Broadcast broadcast = (Broadcast) chanelMap.get("Broadcast");
        return broadcast.isBroadcasting();
    }

    @Override
    public void botGoBroadcasting() {
        Broadcast broadcast = (Broadcast) chanelMap.get("Broadcast");
        broadcast.setBroadcasting(true);
    }

    @Override
    public void botStopBroadcasting() {
        Broadcast broadcast = (Broadcast) chanelMap.get("Broadcast");
        broadcast.setBroadcasting(false);
    }


    public void login(Member member) throws InterruptedException {
        onlineMembers.put(member.getId(), member);
        member.login();
        JsonObject payload = new JsonObject();
        payload.addProperty("member", JsonUtil.toJson(member));
        EventManager.submit(new BotEvent<>(BotEventName.LOGIN, JsonUtil.toJson(payload)));
    }

    public void logout(Member member) throws InterruptedException {
        onlineMembers.remove(member.getId());
        member.logout();
        JsonObject payload = new JsonObject();
        payload.addProperty("member", JsonUtil.toJson(member));
        EventManager.submit(new BotEvent<>(BotEventName.LOGOUT, JsonUtil.toJson(payload)));
    }

    @Override
    public int totalOnlineMember() {
        return onlineMembers.size();
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

    public Map<String, Member> getOnlineMembers() {
        return onlineMembers;
    }

    public void setOnlineMembers(Map<String, Member> onlineMembers) {
        this.onlineMembers = onlineMembers;
    }
}
