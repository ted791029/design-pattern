package com.ted.bot.guards;

import com.ted.bot.states.Questioning;
import com.ted.fsm.Context;
import com.ted.fsm.Guard;

public class QuestioningToThanksForJoining implements Guard {

    @Override
    public boolean evaluate(Context context) {
        Questioning questioning = (Questioning) context.getState();
        return questioning.isEnd();
    }

}
