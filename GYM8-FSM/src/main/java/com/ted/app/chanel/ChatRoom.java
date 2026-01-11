package com.ted.app.chanel;

import java.util.ArrayList;
import java.util.List;

import com.ted.app.Chanel;
import com.ted.app.EventManager;
import com.ted.app.Message;
import com.ted.bot.BotEvent;
import com.ted.bot.BotEventName;
import com.ted.util.JsonUtil;

public class ChatRoom extends Chanel {

    List<Message> messages = new ArrayList<>();

    public void send(Message message) throws InterruptedException {

        if(isUserNotLogin(message.getUser())){
            return;
        }

        messages.add(message);
        EventManager.submit(new BotEvent(BotEventName.NEW_MESSAGE.getName(), JsonUtil.toJson(message)));
    }

    //==============================

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
