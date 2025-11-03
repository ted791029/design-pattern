package com.ted.app;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private String name;

    private List<ChannelObserver> observers;

    private List<Video> videos;

    private List<ChannelObserver> toBeRemoved;

    public Channel(String name) {
        this.name = name;
        observers = new ArrayList<>();
        videos = new ArrayList<>();
        toBeRemoved = new ArrayList<>();
    }

    public void channelNotify(){
        for(ChannelObserver observer : observers){
            observer.update();
        }
        removeObserver();
    }

    public Video getLastVideo(){
        return videos.get(videos.size() - 1);
    }

    public void subscribe(User user, NotificationResponseType responseType){
        ChannelSubscriber subscriber = new ChannelSubscriber(user.getName(), responseType, this);
        register(subscriber);
        System.out.printf("%s 訂閱了 %s。\n", subscriber.getName(), name);
    }

    public void unsubscribe(ChannelSubscriber subscriber){
        toBeRemoved.add(subscriber);
        System.out.printf("%s 解除訂閱了 %s。\n", subscriber.getName(), name);
    }

    public void update(Video video){
        videos.add(video);
        System.out.printf("頻道 %s 上架了一則新影片 \"%s\"。\n", name, video.getTitle());
        channelNotify();
    }

    private void register(ChannelObserver observer){
        observers.add(observer);
    }

    private void unregister(ChannelObserver observer){
        observers.remove(observer);
    }

    private void removeObserver(){
        for (ChannelObserver observer : toBeRemoved){
            unregister(observer);
        }
        toBeRemoved = new ArrayList<>();
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

    public List<ChannelObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<ChannelObserver> observers) {
        this.observers = observers;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<ChannelObserver> getToBeRemoved() {
        return toBeRemoved;
    }

    public void setToBeRemoved(List<ChannelObserver> toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }
}
