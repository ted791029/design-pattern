package com.ted.app.waterballCommunity.chanel;

import com.ted.app.waterballCommunity.Chanel;
import com.ted.app.waterballCommunity.EventManager;
import com.ted.app.waterballCommunity.Member;
import com.ted.app.waterballCommunity.Speak;
import com.ted.bot.BotEvent;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class Broadcast extends Chanel {

    private boolean isBroadcasting = false;

    private List<Speak> speaks = new ArrayList<>();

    public void send(Speak speak) throws InterruptedException {

        if (isMemberNotLogin(speak.getMember())) {
            return;
        }

        speaks.add(speak);
        EventManager.submit(new BotEvent<>(BotEventName.SPEAK, JsonUtil.toJson(speak)));
    }

    public void goBroadcasting(Member member) throws InterruptedException {

        if (isMemberNotLogin(member)) {
            return;
        }

        if (isBroadcasting) {
            //TODO 重複廣播的處理
        }

        isBroadcasting = true;
        EventManager.submit(new BotEvent<>(BotEventName.GO_BROADCASTING, JsonUtil.toJson(member)));
    }

    public void stopBroadcasting(Member member) throws InterruptedException {

        if (isMemberNotLogin(member)) {
            return;
        }

        if (!isBroadcasting) {
            //TODO 沒廣播的處理
        }

        isBroadcasting = false;
        EventManager.submit(new BotEvent<>(BotEventName.STOP_BROADCASTING, JsonUtil.toJson(member)));
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
