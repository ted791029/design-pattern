package com.ted.bot.guards;

import com.ted.bot.Community;
import com.ted.fsm.Context;
import com.ted.fsm.Guard;

public class DefaultConversationUserMaxLimit implements Guard {

    private Community community;

    public DefaultConversationUserMaxLimit(Community community) {
        this.community = community;
    }

    @Override
    public boolean evaluate(Context context) {
        // 加上bot為10人
        return community.totalOnlineUser() >= 9;
    }
}
