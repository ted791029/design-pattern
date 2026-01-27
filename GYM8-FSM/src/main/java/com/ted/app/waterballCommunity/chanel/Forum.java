package com.ted.app.waterballCommunity.chanel;

import com.ted.app.waterballCommunity.Chanel;
import com.ted.app.waterballCommunity.EventManager;
import com.ted.app.waterballCommunity.Post;
import com.ted.bot.BotEvent;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class Forum extends Chanel {

    private List<Post> posts = new ArrayList<>();

    public void send(Post post) throws InterruptedException {

        if (isMemberNotLogin(post.getMember())) {
            return;
        }

        posts.add(post);

        EventManager.submit(new BotEvent<>(BotEventName.NEW_POST, JsonUtil.toJson(post)));
    }

    //==================================

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
