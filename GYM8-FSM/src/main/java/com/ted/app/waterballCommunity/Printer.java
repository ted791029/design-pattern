package com.ted.app.waterballCommunity;

import com.ted.app.BotEventName;
import com.ted.bot.BotEvent;

public class Printer {

    private PrintHandler handler;

    public Printer(PrintHandler handler) {
        this.handler = handler;
    }

    public void print(BotEvent<BotEventName> botEvent) {
        handler.handle(botEvent);
    }

    //=============================

    public PrintHandler getHandler() {
        return handler;
    }

    public void setHandler(PrintHandler handler) {
        this.handler = handler;
    }
}
