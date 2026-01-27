package com.ted.app.waterballCommunityBot;

import com.ted.app.BotEventName;
import com.ted.app.BotStatus;
import com.ted.app.waterballCommunity.CommunityBot;
import com.ted.bot.BotEvent;
import com.ted.bot.BotFSMFacade;

import java.util.Map;

public class Bot implements CommunityBot {

    private BotFSMFacade<BotStatus, BotEventName> botFacade;

    private Community community;

    private int quota;


    public Bot() {

    }

    public void useQuota(int quota) {
        setQuota(this.quota - quota);
    }

    public Map<String, String> sendEvent(BotEvent<BotEventName> event) {
        return botFacade.sendEvent(event);
    }

    //=======================


    public BotFSMFacade<BotStatus, BotEventName> getBotFacade() {
        return botFacade;
    }

    public void setBotFacade(BotFSMFacade<BotStatus, BotEventName> botFacade) {
        this.botFacade = botFacade;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {

        if (quota <= 0) {
            this.quota = 0;
            return;
        }

        this.quota = quota;
    }
}
