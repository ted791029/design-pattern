package com.ted.app.chanel;

import com.google.gson.JsonObject;
import com.ted.app.Chanel;
import com.ted.app.EventManager;
import com.ted.app.Post;
import com.ted.bot.BotEvent;
import com.ted.bot.BotEventName;
import com.ted.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class Forum extends Chanel {

    private List<Post> posts = new ArrayList<>();

    public void send(Post post) throws InterruptedException {

        if(isUserNotLogin(post.getUser())){
            return;
        }

        posts.add(post);

        EventManager.submit(new BotEvent(BotEventName.NEW_POST.getName(), JsonUtil.toJson(post)));
    }

    //==================================

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
