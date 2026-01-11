package com.ted.bot;

import com.ted.bot.actions.*;
import com.ted.bot.guards.*;
import com.ted.bot.stateResponseHandlers.*;
import com.ted.bot.states.*;
import com.ted.bot.states.Record;
import com.ted.fsm.Event;
import com.ted.fsm.FiniteStateMachine;
import com.ted.fsm.Transition;

import java.util.*;

public class BotFacade {

    private Bot bot;

    private Community community;

    public BotFacade(Community community, int quota) {
        this.bot = new Bot();
        this.community = community;
        build(quota);
    }

    public Map<String, String> sendEvent(Event event) {
        return bot.sendEvent(event);
    }

    private void build(int quota) {
        FiniteStateMachine finiteStateMachine = buildFiniteStateMachine();
        bot.setQuota(quota);
        bot.setFiniteStateMachine(finiteStateMachine);
    }

    private FiniteStateMachine buildFiniteStateMachine() {
        Map<String, String> resultMap = new LinkedHashMap<>();
        Normal normal = buildNormalState(resultMap);
        Record record = buildRecordState(resultMap);
        KnowledgeKing knowledgeKing = buildKnowledgeKingState(resultMap);
        List<Transition> machineTransitions = new ArrayList<>();
        NormalToRecordWithCommand normalToRecordWithCommand = new NormalToRecordWithCommand(bot);
        NormalToKnowledgeKingWithCommand normalToKnowledgeKingWithCommand = new NormalToKnowledgeKingWithCommand(bot);
        RecordToNormalCommand recordToNormalCommand = new RecordToNormalCommand();
        KnowledgeKingToNormalCommand knowledgeKingToNormalCommand = new KnowledgeKingToNormalCommand();
        machineTransitions.add(new Transition(null, new BotEvent(BotEventName.NEW_MESSAGE.getName(), ""), normal, normalToRecordWithCommand, record));
        machineTransitions.add(new Transition(null, new BotEvent(BotEventName.NEW_MESSAGE.getName(), ""), normal, normalToKnowledgeKingWithCommand, knowledgeKing));
        machineTransitions.add(new Transition(null, new BotEvent(BotEventName.KNOWLEDGE_KING_IS_END.getName(), ""), knowledgeKing, null, normal));
        machineTransitions.add(new Transition(null, new BotEvent(BotEventName.NEW_MESSAGE.getName(), ""), record, recordToNormalCommand, normal));
        machineTransitions.add(new Transition(null, new BotEvent(BotEventName.NEW_MESSAGE.getName(), ""), knowledgeKing, knowledgeKingToNormalCommand, normal));
        return new FiniteStateMachine(normal, machineTransitions, resultMap);
    }

    private Normal buildNormalState(Map<String, String> resultMap) {
        //預設對話
        StateResponseHandler defaultConversationHandler = new MessageWithConversationHandler(new PostWithConversationHandler(null));
        DefaultConversationEntryAction defaultConversationEntryAction = new DefaultConversationEntryAction();
        DefaultConversation defaultConversation = new DefaultConversation(null, null, resultMap, defaultConversationEntryAction, null, defaultConversationHandler);
        //互動狀態
        StateResponseHandler interactingHandler = new MessageWithInteractingHandler(new PostWithInteractingHandler(null, community));
        InteractingEntryAction interactingEntryAction = new InteractingEntryAction();
        Interacting interacting = new Interacting(null, null, resultMap, interactingEntryAction, null, interactingHandler);
        List<Transition> transitions = new ArrayList<>();
        DefaultConversationUserMaxLimit defaultConversationUserMaxLimit = new DefaultConversationUserMaxLimit(community);
        transitions.add(new Transition(null, new BotEvent(BotEventName.LOGIN.getName(), ""), defaultConversation, defaultConversationUserMaxLimit, interacting));
        InteractingUserMinLimit interactingUserMinLimit = new InteractingUserMinLimit(community);
        transitions.add(new Transition(null, new BotEvent(BotEventName.LOGOUT.getName(), ""), interacting, interactingUserMinLimit, defaultConversation));
        return new Normal(defaultConversation, transitions, resultMap, null, null);
    }


    private Record buildRecordState(Map<String, String> resultMap) {
        Map<String, Record> childrenMap = new HashMap<>();
        Id recorderId = new Id();
        //錄音-等待
        //狀態
        Waiting waiting = new Waiting(null, null, resultMap, null, null, recorderId, childrenMap);
        //錄音-錄音中
        //動作
        RecordingExitAction recordingExitAction = new RecordingExitAction();
        //狀態
        Recording recording = new Recording(null, null, resultMap, null, recordingExitAction, recorderId, childrenMap);
        List<Transition> transitions = new ArrayList<>();
        transitions.add(new Transition(null, new BotEvent(BotEventName.GO_BROADCASTING.getName(), ""), waiting, null, recording));
        transitions.add(new Transition(null, new BotEvent(BotEventName.STOP_BROADCASTING.getName(), ""), recording, null, waiting));
        //錄音
        //動作
        RecordEntryAction recordEntryAction = new RecordEntryAction(community);
        childrenMap.put("Waiting", waiting);
        childrenMap.put("Recording", recording);
        return new Record(null, transitions, resultMap, recordEntryAction, null, recorderId, childrenMap);
    }

    private KnowledgeKing buildKnowledgeKingState(Map<String, String> resultMap){
        Map<String, Integer> scoreMap = new HashMap<>();
        QuestioningEntryAction questioningEntryAction = new QuestioningEntryAction();
        StateResponseHandler questioningHandler = new MessageWithQuestioningHandler( new TimeElapsedWithQuestioningHandler(null));
        Questioning questioning = new Questioning(null, null, resultMap, questioningEntryAction, null, scoreMap, questioningHandler);
        TanksForJoiningEntryAction tanksForJoiningEntryAction = new TanksForJoiningEntryAction();
        ThanksForJoining thanksForJoining = new ThanksForJoining(null, null, resultMap, tanksForJoiningEntryAction, null, scoreMap);
        List<Transition> transitions = new ArrayList<>();
        QuestioningToThanksForJoining questioningToThanksForJoining = new QuestioningToThanksForJoining();
        QuestioningToThanksForJoiningAction questioningToThanksForJoiningAction = new QuestioningToThanksForJoiningAction(community);
        transitions.add(new Transition(questioningToThanksForJoiningAction, new BotEvent(BotEventName.NEW_MESSAGE.getName(), ""), questioning, questioningToThanksForJoining, thanksForJoining));
        transitions.add(new Transition(questioningToThanksForJoiningAction, new BotEvent(BotEventName.QUESTIONING_IS_END.getName(), ""), questioning, questioningToThanksForJoining, thanksForJoining));
        ThanksForJoiningToQuestioningCommand thanksForJoiningToQuestioningCommand = new ThanksForJoiningToQuestioningCommand(bot);
        ThanksForJoiningToQuestioningAction thanksForJoiningToQuestioningAction = new ThanksForJoiningToQuestioningAction();
        transitions.add(new Transition(thanksForJoiningToQuestioningAction, new BotEvent(BotEventName.NEW_MESSAGE.getName(), ""),  thanksForJoining, thanksForJoiningToQuestioningCommand, questioning));
        return new KnowledgeKing(questioning, transitions, resultMap, null, null, scoreMap);
    }


    //===========================

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }
}
