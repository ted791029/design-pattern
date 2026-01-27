package com.ted.app.waterballCommunity.chanel;

import com.ted.app.waterballCommunity.Chanel;
import com.ted.app.waterballCommunity.EventManager;
import com.ted.app.waterballCommunity.Message;
import com.ted.bot.BotEvent;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom extends Chanel {

    List<Message> messages = new ArrayList<>();

    public void send(Message message) throws InterruptedException {

        if (isMemberNotLogin(message.getMember())) {
            return;
        }

        messages.add(message);
        EventManager.submit(new BotEvent<>(BotEventName.NEW_MESSAGE, JsonUtil.toJson(message)));
    }

    //==============================

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
