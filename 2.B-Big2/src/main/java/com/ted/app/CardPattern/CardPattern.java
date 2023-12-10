package com.ted.app.CardPattern;

import com.ted.app.Card.Card;
import com.ted.app.CardPatternHandler.CardPatternHandler;

import java.util.List;

public class CardPattern {
    List<Card> cards;

    public CardPattern(List<Card> cards){
        setCards(cards);
    }

    public boolean compare(CardPattern pattern){
        List<Card> cards = getCardsToCompare();
        Card biggestCard = findBiggestCard(cards);
        List<Card> patternCards = pattern.getCardsToCompare();
        Card patternBiggestCard = pattern.findBiggestCard(patternCards);
        return biggestCard.compare(patternBiggestCard);
    }

    protected List<Card> getCardsToCompare(){
        return cards;
    };

    protected Card findBiggestCard(List<Card> cards){
        Card biggestCard = null;
        for(Card card : cards){
            if(biggestCard == null){
                biggestCard = card;
                continue;
            }
            if(card.compare(biggestCard)){
                biggestCard = card;
            }
        }
        return biggestCard;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
