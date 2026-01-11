package com.ted.bot.guards;

import com.ted.bot.Community;
import com.ted.fsm.Context;
import com.ted.fsm.Event;
import com.ted.fsm.Guard;

public class InteractingUserMinLimit implements Guard {

    private Community community;

    public InteractingUserMinLimit(Community community) {
        this.community = community;
    }

    @Override
    public boolean evaluate(Context context) {
        //加上bot
        return community.totalOnlineUser() < 9;
    }
}
