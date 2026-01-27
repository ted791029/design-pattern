package com.ted.app.waterballCommunity;

import com.ted.bot.BotEvent;
import com.ted.app.BotEventName;

public abstract class PrintHandler {

    private BotEventName botEventName;

    private String icon;

    private PrintHandler next;

    public PrintHandler(BotEventName botEventName, String icon, PrintHandler next) {
        this.botEventName = botEventName;
        this.icon = icon;
        this.next = next;
    }

    public void handle(BotEvent<BotEventName> botEvent) {
        BotEventName botEventName = botEvent.getEventName();
        String payload = botEvent.getPayload();

        if (match(botEventName)) {
            String result = handling(payload);
            System.out.println(icon + result);

        } else if (next != null) {
            next.handle(botEvent);
        }

    }

    protected boolean match(BotEventName botEventName) {
        return this.botEventName == botEventName;
    }

    abstract protected String handling(String payload);

    //====================================

    public BotEventName getBotEventName() {
        return botEventName;
    }

    public void setBotEventName(BotEventName botEventName) {
        this.botEventName = botEventName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public PrintHandler getNext() {
        return next;
    }

    public void setNext(PrintHandler next) {
        this.next = next;
    }
}
