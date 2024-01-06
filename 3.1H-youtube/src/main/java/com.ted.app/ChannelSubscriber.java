package com.ted.app;

import java.util.ArrayList;
import java.util.List;

public class ChannelSubscriber implements ChannelObserver{
    private String name;
    private List<Video> likeVideos;
    private NotificationResponseType responseType;
    private Channel channel;

    public ChannelSubscriber(String name, NotificationResponseType responseType, Channel channel) {
        setName(name);
        likeVideos = new ArrayList<>();
        setResponseType(responseType);
        setChannel(channel);
    }

    public void like(Video video){
        likeVideos.add(video);
    }

    @Override
    public void update() {
        Video lastVideo = channel.getLastVideo();
        responseType.receiveNotification(lastVideo);
    }

    /**
     * getter & setter
     **/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Video> getLikeVideos() {
        return likeVideos;
    }

    public void setLikeVideos(List<Video> likeVideos) {
        this.likeVideos = likeVideos;
    }

    public NotificationResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(NotificationResponseType responseType) {
        this.responseType = responseType;
        this.responseType.setSubscriber(this);
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
