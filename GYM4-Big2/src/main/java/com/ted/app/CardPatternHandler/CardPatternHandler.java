package com.ted.app.CardPatternHandler;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;

import java.util.List;
import java.util.Optional;

public abstract class CardPatternHandler {
    private CardPatternHandler next;

    public CardPatternHandler(CardPatternHandler next) {
        setNext(next);
    }

    public Optional<CardPattern> handle(List<Card> playCards) {
        Optional<CardPatternHandler> nextOp = Optional.ofNullable(next);
        if (match(playCards)) {
            return doHandling(playCards);
        } else if (nextOp.isPresent()) {
            return next.handle(playCards);
        }
        return Optional.empty();
    }

    public abstract boolean match(List<Card> playCards);

    protected abstract Optional<CardPattern> doHandling(List<Card> playCards);

    /**
     * getter & setter
     **/
    public CardPatternHandler getNext() {
        return next;
    }

    public void setNext(CardPatternHandler next) {
        this.next = next;
    }
}
