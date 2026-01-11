package com.ted.app.chanel;

import com.ted.app.Chanel;
import com.ted.app.EventManager;
import com.ted.app.Speak;
import com.ted.app.User;
import com.ted.bot.BotEvent;
import com.ted.bot.BotEventName;
import com.ted.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class Broadcast extends Chanel {

    private boolean isBroadcasting = false;

    private List<Speak> speaks = new ArrayList<>();

    public void send(Speak speak) throws InterruptedException {

        if(isUserNotLogin(speak.getUser())){
            return;
        }

        speaks.add(speak);
        EventManager.submit(new BotEvent(BotEventName.SPEAK.getName(), JsonUtil.toJson(speak)));
    }

    public void goBroadcasting(User user) throws InterruptedException {

        if(isUserNotLogin(user)){
            return;
        }

        if(isBroadcasting){
            //TODO 重複廣播的處理
        }

        isBroadcasting = true;
        EventManager.submit(new BotEvent(BotEventName.GO_BROADCASTING.getName(), JsonUtil.toJson(user)));
    }

    public void stopBroadcasting(User user) throws InterruptedException {

        if(isUserNotLogin(user)){
            return;
        }

        if(!isBroadcasting){
            //TODO 沒廣播的處理
        }

        isBroadcasting = false;
        EventManager.submit(new BotEvent(BotEventName.STOP_BROADCASTING.getName(), JsonUtil.toJson(user)));
    }

    //=====================================


    public boolean isBroadcasting() {
        return isBroadcasting;
    }

    public void setBroadcasting(boolean broadcasting) {
        isBroadcasting = broadcasting;
    }

    public List<Speak> getSpeaks() {
        return speaks;
    }

    public void setSpeaks(List<Speak> speaks) {
        this.speaks = speaks;
    }
}
