package com.ted.app.waterballCommunityBot;

import java.util.HashMap;
import java.util.Map;

import com.ted.app.BotEventName;
import com.ted.app.BotStatus;
import com.ted.app.waterballCommunityBot.actions.DefaultConversationEntryAction;
import com.ted.app.waterballCommunityBot.actions.InteractingEntryAction;
import com.ted.app.waterballCommunityBot.actions.QuestioningEntryAction;
import com.ted.app.waterballCommunityBot.actions.QuestioningToThanksForJoiningAction;
import com.ted.app.waterballCommunityBot.actions.RecordEntryAction;
import com.ted.app.waterballCommunityBot.actions.RecordingExitAction;
import com.ted.app.waterballCommunityBot.actions.TanksForJoiningEntryAction;
import com.ted.app.waterballCommunityBot.actions.ThanksForJoiningToQuestioningAction;
import com.ted.app.waterballCommunityBot.guards.DefaultConversationMemberMaxLimit;
import com.ted.app.waterballCommunityBot.guards.InteractingMemberMinLimit;
import com.ted.app.waterballCommunityBot.guards.KnowledgeKingToNormalCommand;
import com.ted.app.waterballCommunityBot.guards.NormalToKnowledgeKingWithCommand;
import com.ted.app.waterballCommunityBot.guards.NormalToRecordWithCommand;
import com.ted.app.waterballCommunityBot.guards.QuestioningToThanksForJoining;
import com.ted.app.waterballCommunityBot.guards.RecordToNormalCommand;
import com.ted.app.waterballCommunityBot.guards.ThanksForJoiningToQuestioningCommand;
import com.ted.app.waterballCommunityBot.stateResponseHandlers.MessageWithConversationHandler;
import com.ted.app.waterballCommunityBot.stateResponseHandlers.MessageWithInteractingHandler;
import com.ted.app.waterballCommunityBot.stateResponseHandlers.MessageWithQuestioningHandler;
import com.ted.app.waterballCommunityBot.stateResponseHandlers.PostWithConversationHandler;
import com.ted.app.waterballCommunityBot.stateResponseHandlers.PostWithInteractingHandler;
import com.ted.app.waterballCommunityBot.stateResponseHandlers.TimeElapsedWithQuestioningHandler;
import com.ted.app.waterballCommunityBot.states.DefaultConversation;
import com.ted.app.waterballCommunityBot.states.Interacting;
import com.ted.app.waterballCommunityBot.states.KnowledgeKing;
import com.ted.app.waterballCommunityBot.states.Normal;
import com.ted.app.waterballCommunityBot.states.Questioning;
import com.ted.app.waterballCommunityBot.states.Record;
import com.ted.app.waterballCommunityBot.states.Recording;
import com.ted.app.waterballCommunityBot.states.ThanksForJoining;
import com.ted.app.waterballCommunityBot.states.Waiting;
import com.ted.bot.BotAbstractState;
import com.ted.bot.BotEvent;
import com.ted.bot.BotFSMConfigurer;
import com.ted.bot.BotFSMFacade.StatesConfigurer;
import com.ted.bot.BotFSMFacade.FiniteStateMachineConfigurer;

public class BotConfigurerAdapter implements BotFSMConfigurer<BotStatus, BotEventName> {

    private final Community community;

    private final Bot bot;

    public BotConfigurerAdapter(Community community, Bot bot) {
        this.community = community;
        this.bot = bot;
    }

    @Override
    public void configure(StatesConfigurer<BotStatus, BotEventName> states) {
        setNormalState(states);
        setRecordState(states);
        setKnowledgeKingState(states);
    }

    @Override
    public void configure(FiniteStateMachineConfigurer<BotStatus, BotEventName> fsm) {
        setNormalFSM(fsm);
        setRecordFSM(fsm);
        setKnowledgeKingFSM(fsm);
        setRootFSM(fsm);
    }

    private void setNormalState(StatesConfigurer<BotStatus, BotEventName> states) {
        states
                .componentState(
                        Normal::new,
                        BotStatus.NORMAL
                )
                .leafState(
                        (enter, exit) -> new DefaultConversation(enter, exit, new MessageWithConversationHandler(new PostWithConversationHandler(null))),
                        new DefaultConversationEntryAction(),
                        BotStatus.DEFAULT_CONVERSATION
                )
                .leafState(
                        (enter, exit) -> new Interacting(enter, exit, new MessageWithInteractingHandler(new PostWithInteractingHandler(null, community))),
                        new InteractingEntryAction(),
                        BotStatus.INTERACTING
                );
    }

    private void setNormalFSM(FiniteStateMachineConfigurer<BotStatus, BotEventName> fsm) {
        fsm
                .child(BotStatus.NORMAL)
                .initial(BotStatus.DEFAULT_CONVERSATION)
                .withTransition()
                    .event(new BotEvent<>(BotEventName.LOGIN))
                    .form(BotStatus.DEFAULT_CONVERSATION)
                    .guard(new DefaultConversationMemberMaxLimit(community))
                    .to(BotStatus.INTERACTING)
                .and()
                .withTransition()
                    .event(new BotEvent<>(BotEventName.LOGOUT))
                    .form(BotStatus.INTERACTING)
                    .guard(new InteractingMemberMinLimit(community))
                    .to(BotStatus.DEFAULT_CONVERSATION)
                .and()
                .childEnd();
    }

    private void setKnowledgeKingState(StatesConfigurer<BotStatus, BotEventName> states) {
        Map<String, Integer> scoreMap = new HashMap<>();
        states
                .componentState(
                        (initial, transitions, enter, exit) -> new KnowledgeKing(initial, transitions, enter, exit, scoreMap),
                        BotStatus.KNOWLEDGE_KING
                )
                .leafState(
                        (enter, exit) -> new Questioning(enter, exit, scoreMap, new MessageWithQuestioningHandler(new TimeElapsedWithQuestioningHandler(null))),
                        new QuestioningEntryAction(),
                        BotStatus.QUESTIONING
                )
                .leafState(
                        ThanksForJoining::new,
                        new TanksForJoiningEntryAction(),
                        BotStatus.THANKS_FOR_JOINING
                );
    }

    private void setKnowledgeKingFSM(FiniteStateMachineConfigurer<BotStatus, BotEventName> fsm) {
        fsm
                .child(BotStatus.KNOWLEDGE_KING)
                .initial(BotStatus.QUESTIONING)
                .withTransition()
                    .event(new BotEvent<>(BotEventName.NEW_MESSAGE))
                    .form(BotStatus.QUESTIONING)
                    .action(new QuestioningToThanksForJoiningAction(community))
                    .guard(new QuestioningToThanksForJoining())
                    .to(BotStatus.THANKS_FOR_JOINING)
                .and()
                .withTransition()
                    .event(new BotEvent<>(BotEventName.QUESTIONING_IS_END))
                    .form(BotStatus.QUESTIONING)
                    .action(new QuestioningToThanksForJoiningAction(community))
                    .guard(new QuestioningToThanksForJoining())
                    .to(BotStatus.THANKS_FOR_JOINING)
                .and()
                .withTransition()
                    .event(new BotEvent<>(BotEventName.NEW_MESSAGE))
                    .form(BotStatus.THANKS_FOR_JOINING)
                    .action(new ThanksForJoiningToQuestioningAction())
                    .guard(new ThanksForJoiningToQuestioningCommand(bot))
                    .to(BotStatus.QUESTIONING)
                .and()
                .childEnd();
    }


    private void setRecordState(StatesConfigurer<BotStatus, BotEventName> states) {
        Map<String, BotAbstractState<BotEventName>> childrenMap = new HashMap();
        Id recorderId = new Id();
        states
                .componentState(
                        (initial, transitions, enter, exit) -> new Record(initial, transitions, enter, exit, recorderId, childrenMap),
                        new RecordEntryAction(community),
                        BotStatus.RECORD
                )
                .leafState(
                        Waiting::new,
                        BotStatus.WAITING
                )
                .leafState(
                        (enter, exit) -> new Recording(enter, exit, recorderId),
                        null,
                        new RecordingExitAction(),
                        BotStatus.RECORDING
                );
        childrenMap.put("Waiting", states.getState(BotStatus.WAITING));
        childrenMap.put("Recording", states.getState(BotStatus.RECORDING));
    }

    private void setRecordFSM(FiniteStateMachineConfigurer<BotStatus, BotEventName> fsm) {
        fsm
                .child(BotStatus.RECORD)
                .withTransition()
                    .event(new BotEvent<>(BotEventName.GO_BROADCASTING))
                    .form(BotStatus.WAITING)
                    .to(BotStatus.RECORDING)
                .and()
                .withTransition()
                    .event(new BotEvent<>(BotEventName.STOP_BROADCASTING))
                    .form(BotStatus.RECORDING)
                    .to(BotStatus.WAITING)
                .and()
                .childEnd();
    }

    private void setRootFSM(FiniteStateMachineConfigurer<BotStatus, BotEventName> fsm) {
        fsm
                .initial(BotStatus.NORMAL)
                .withTransition()
                    .event(new BotEvent<>(BotEventName.NEW_MESSAGE))
                    .form(BotStatus.NORMAL)
                    .guard(new NormalToRecordWithCommand(bot))
                    .to(BotStatus.RECORD)
                .and()
                .withTransition()
                    .event(new BotEvent<>(BotEventName.NEW_MESSAGE))
                    .form(BotStatus.NORMAL)
                    .guard(new NormalToKnowledgeKingWithCommand(bot))
                    .to(BotStatus.KNOWLEDGE_KING)
                .and()
                .withTransition()
                    .event(new BotEvent<>(BotEventName.KNOWLEDGE_KING_IS_END))
                    .form(BotStatus.KNOWLEDGE_KING)
                    .to(BotStatus.NORMAL)
                .and()
                .withTransition()
                    .event(new BotEvent<>(BotEventName.NEW_MESSAGE))
                    .guard(new RecordToNormalCommand())
                    .form(BotStatus.RECORD)
                    .to(BotStatus.NORMAL)
                .and()
                .withTransition()
                    .event(new BotEvent<>(BotEventName.NEW_MESSAGE))
                    .form(BotStatus.KNOWLEDGE_KING)
                    .guard(new KnowledgeKingToNormalCommand())
                    .to(BotStatus.NORMAL)
                .and();
    }
}
