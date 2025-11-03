package com.ted.app;

public abstract class NotificationResponseType {

    private ChannelSubscriber subscriber;

    public abstract void receiveNotification(Video lastVideo);

    /**
     * getter & setter
     **/

    public ChannelSubscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(ChannelSubscriber subscriber) {
        this.subscriber = subscriber;
    }
}
