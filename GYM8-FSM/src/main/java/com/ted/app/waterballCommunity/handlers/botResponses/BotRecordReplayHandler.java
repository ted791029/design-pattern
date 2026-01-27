package com.ted.app.waterballCommunity.handlers.botResponses;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.app.waterballCommunity.BotResponseHandler;
import com.ted.app.waterballCommunity.Member;
import com.ted.app.waterballCommunity.Message;
import com.ted.app.waterballCommunity.Tag;
import com.ted.bot.BotEvent;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class BotRecordReplayHandler extends BotResponseHandler {


    public BotRecordReplayHandler(BotResponseHandler next) {
        super(next, BotEventResult.BOT_RECORD_REPLAY);
    }

    @Override
    protected BotEvent<BotEventName> handling(JsonObject jsonObject) {
        String content = jsonObject.get("content").getAsString();
        JsonArray tagsArray = jsonObject.getAsJsonArray("tags");
        Member member = new Member("", false);

        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < tagsArray.size(); i++) {
            String tagValue = tagsArray.get(i).getAsString();
            tags.add(new Tag(tagValue));
        }

        Message message = new Message(content, tags, member);
        return new BotEvent<>(BotEventName.BOT_NEW_MESSAGE, JsonUtil.toJson(message));
    }
}
