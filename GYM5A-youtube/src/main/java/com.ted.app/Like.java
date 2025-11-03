package com.ted.app;

public class Like extends NotificationResponseType{

    @Override
    public void receiveNotification(Video lastVideo) {
        if(lastVideo.getLength() >= 180){
            System.out.printf("%s 對影片 \"%s\" 按讚。\n", super.getSubscriber().getName(), lastVideo.getTitle());
            super.getSubscriber().like(lastVideo);
        }
    }
}
