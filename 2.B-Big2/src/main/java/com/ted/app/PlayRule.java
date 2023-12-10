package com.ted.app;

import com.ted.app.Card.Card;
import com.ted.app.Card.Rank;
import com.ted.app.Card.Suit;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.CardPatternHandler.*;

import java.util.List;
import java.util.Optional;

public class PlayRule {
    private Round round;

    private CardPatternHandler handler;

    public PlayRule(){
        handlerInit();
    }

    public boolean isValid(List<Card> playCards, Optional<CardPattern> playPatternOp){
        Optional<CardPattern> topPlayOp =  round.getTopPlay();
        if(playPatternOp.isEmpty()) return false;
        if(round.getCount() == 1 && topPlayOp.isEmpty()){
            return isContainClubsThree(playCards);
        }else {
            CardPattern pattern = playPatternOp.get();
            CardPattern topPlay = topPlayOp.get();
            if (pattern.getClass() != topPlay.getClass()) return false;
            return pattern.compare(topPlay);
        }
    }

    public Optional<CardPattern> getPlayPatternOp(List<Card> playCards){
        return handler.handle(playCards);
    }

    private boolean isContainClubsThree(List<Card> playCards){
        for(Card card : playCards){
            if(card.getRank() == Rank.THREE && card.getSuit() == Suit.CLUB) return true;
        }
        return false;
    }


    private void handlerInit(){
        handler =  new SingleHandler(
                new PairHandler(
                        new StraightHandler(
                                new FullHouseHandler(
                                        null
                                )
                        )
                )
        );
    }

    /**getter & setter**/
    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }
    public CardPatternHandler getHandler() {
        return handler;
    }

    public void setHandler(CardPatternHandler handler) {
        this.handler = handler;
    }
}
