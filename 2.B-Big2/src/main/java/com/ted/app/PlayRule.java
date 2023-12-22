package com.ted.app;

import com.ted.app.Card.Card;
import com.ted.app.Card.Rank;
import com.ted.app.Card.Suit;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.CardPatternHandler.*;

import java.util.List;
import java.util.Optional;

public class PlayRule {

    private CardPatternHandler handler;

    public PlayRule() {
        handlerInit();
    }

    public boolean isValid(Optional<CardPattern> playPattern, Round round) {
        Optional<CardPattern> topPlayOp = round.getTopPlay();
        if (playPattern.isEmpty()) {
            System.out.printf("此牌型不合法，請再嘗試一次。\n");
            return false;
        }
        if (round.getCount() == 1 && topPlayOp.isEmpty()) {
            if (!isContainClubsThree(playPattern.get().getCards())) {
                System.out.printf("首次出牌需包含梅花3。\n");
                return false;
            }
            return true;
        } else {
            if (topPlayOp.isEmpty()) return true;
            CardPattern pattern = playPattern.get();
            CardPattern topPlay = topPlayOp.get();
            if (pattern.getClass() != topPlay.getClass()) {
                System.out.printf("目前無法出此類牌型。\n");
                return false;
            }
            if (!pattern.compare(topPlay)) {
                System.out.printf("牌型需大於檯面。\n");
                return false;
            }
        }
        return true;
    }

    public Optional<CardPattern> getPlayPatternOp(List<Card> playCards) {
        return handler.handle(playCards);
    }

    private boolean isContainClubsThree(List<Card> playCards) {
        for (Card card : playCards) {
            if (card.getRank() == Rank.THREE && card.getSuit() == Suit.CLUB) return true;
        }
        return false;
    }


    private void handlerInit() {
        handler = new SingleHandler(
                new PairHandler(
                        new StraightHandler(
                                new FullHouseHandler(
                                        null
                                )
                        )
                )
        );
    }

    /**
     * getter & setter
     **/
    public CardPatternHandler getHandler() {
        return handler;
    }

    public void setHandler(CardPatternHandler handler) {
        this.handler = handler;
    }
}
