package com.ted.bot;

import com.ted.bot.BotFSMFacade.FiniteStateMachineConfigurer;
import com.ted.bot.BotFSMFacade.StatesConfigurer;

public interface BotFSMConfigurer<S, EN> {

    public void configure(StatesConfigurer<S, EN> states);

    public void configure(FiniteStateMachineConfigurer<S, EN> fsm);
}
