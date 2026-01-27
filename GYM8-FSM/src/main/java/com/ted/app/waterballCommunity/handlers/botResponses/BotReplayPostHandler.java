package com.ted.app.waterballCommunity.handlers.botResponses;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.app.waterballCommunity.BotResponseHandler;
import com.ted.app.waterballCommunity.Comment;
import com.ted.app.waterballCommunity.Member;
import com.ted.app.waterballCommunity.Tag;
import com.ted.bot.BotEvent;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class BotReplayPostHandler extends BotResponseHandler {
    public BotReplayPostHandler(BotResponseHandler next) {
        super(next, BotEventResult.BOT_REPLY_POST);
    }

    @Override
    protected BotEvent<BotEventName> handling(JsonObject jsonObject) {
        String postId = jsonObject.get("postId").getAsString();
        String content = jsonObject.get("content").getAsString();
        JsonArray tagsArray = jsonObject.getAsJsonArray("tags");
        Member member = new Member("", false);

        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < tagsArray.size(); i++) {
            String tagValue = tagsArray.get(i).getAsString();
            tags.add(new Tag(tagValue));
        }

        Comment comment = new Comment(content, postId, tags, member);
        return new BotEvent<>(BotEventName.BOT_NEW_COMMENT, JsonUtil.toJson(comment));
    }
}
