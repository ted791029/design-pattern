package com.ted.app.Player;

import com.ted.app.AiPlayHandler.AIPlayHandler;
import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AIPlayer extends Player {

    private AIPlayHandler aiPlayHandler;

    public AIPlayer(int no, String name, AIPlayHandler handler) {
        super(no);
        super.setName(name);
        setAiPlayHandler(handler);
    }

    @Override
    public List<Card> play(Scanner scanner, Optional<CardPattern> topPlay) {
        if (topPlay.isEmpty()) {
            List<Card> playCards = new ArrayList<>();
            Card card = super.getHand().fristCard();
            playCards.add(card);
            return playCards;
        }
        return aiPlayHandler.handle(topPlay.get(), super.getHand());
    }

    @Override
    public void showHand() {
    }

    ;

    public AIPlayHandler getAiPlayHandler() {
        return aiPlayHandler;
    }

    public void setAiPlayHandler(AIPlayHandler aiPlayHandler) {
        this.aiPlayHandler = aiPlayHandler;
    }
}
