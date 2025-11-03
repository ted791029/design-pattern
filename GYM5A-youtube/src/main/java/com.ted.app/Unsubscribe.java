package com.ted.app;

public class Unsubscribe extends NotificationResponseType{

    @Override
    public void receiveNotification(Video lastVideo) {
        if(lastVideo.getLength() <= 60){
            Channel channel = super.getSubscriber().getChannel();
            channel.unsubscribe(super.getSubscriber());
        }
    }
}
