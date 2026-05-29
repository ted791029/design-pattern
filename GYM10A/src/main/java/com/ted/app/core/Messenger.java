package com.ted.app.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Messenger {

    private List<Message> messages = new ArrayList<>();


    public Messenger(Message... initialMessages) {
        messages.addAll(Arrays.asList(initialMessages));
    }

    public void add(Message message) {
        messages.add(message);
    }

    public String displayText() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            if (i > 0) {
                text.append(System.lineSeparator());
            }
            text.append(messages.get(i).getText());
        }
        return text.toString();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return messages.size();
    }

    // Getters and setters
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
