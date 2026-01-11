package com.ted.bot.actions;

import com.ted.bot.states.ThanksForJoining;
import com.ted.fsm.Action;
import com.ted.fsm.Context;

public class TanksForJoiningEntryAction implements Action {
    @Override
    public void execute(Context context) {
        ThanksForJoining thanksForJoining = (ThanksForJoining) context.getState();
        thanksForJoining.initTimer();
    }
}
