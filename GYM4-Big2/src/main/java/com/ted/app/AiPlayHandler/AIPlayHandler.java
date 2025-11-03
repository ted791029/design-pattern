package com.ted.app.AiPlayHandler;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.CardPatternHandler.CardPatternHandler;
import com.ted.app.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AIPlayHandler {
    private AIPlayHandler next;

    protected CardPatternHandler cardPatternHandler;

    public AIPlayHandler(AIPlayHandler next, CardPatternHandler cardPatternHandler) {
        setNext(next);
        setCardPatternHandler(cardPatternHandler);
    }

    public List<Card> handle(CardPattern topPlay, Hand hand) {
        Optional<AIPlayHandler> nextOp = Optional.ofNullable(next);
        if (match(topPlay)) {
            return doHandling(topPlay, hand);
        } else if (nextOp.isPresent()) {
            return nextOp.get().handle(topPlay, hand);
        }
        return new ArrayList<>();
    }

    protected abstract boolean match(CardPattern topPlay);

    protected abstract List<Card> doHandling(CardPattern topPlay, Hand hand);

    public AIPlayHandler getNext() {
        return next;
    }

    public void setNext(AIPlayHandler next) {
        this.next = next;
    }

    public CardPatternHandler getCardPatternHandler() {
        return cardPatternHandler;
    }

    public void setCardPatternHandler(CardPatternHandler cardPatternHandler) {
        this.cardPatternHandler = cardPatternHandler;
    }
}
