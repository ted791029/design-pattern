package com.ted.bot.actions;

import com.ted.bot.states.Interacting;
import com.ted.fsm.Action;
import com.ted.fsm.Context;

public class InteractingEntryAction implements Action {
    @Override
    public void execute(Context context) {
        Interacting interacting = (Interacting) context.getState();
        interacting.resetIndex();
    }
}
