package com.ted.app.CardPattern;

import com.ted.app.Card.Card;

import java.util.List;

public class CardPattern {
    private List<Card> cards;
    private String name;
    private int cardSize;
    private int rankTypeSize;

    public CardPattern(List<Card> cards, String name, int cardSize, int rankTypeSize) {
        setCards(cards);
        setName(name);
        setCardSize(cardSize);
        setRankTypeSize(rankTypeSize);
    }

    public boolean compare(CardPattern pattern) {
        List<Card> cards = getCardsToCompare();
        Card biggestCard = findBiggestCard(cards);
        List<Card> patternCards = pattern.getCardsToCompare();
        Card patternBiggestCard = pattern.findBiggestCard(patternCards);
        return biggestCard.compare(patternBiggestCard);
    }

    protected List<Card> getCardsToCompare() {
        return cards;
    }

    ;

    protected Card findBiggestCard(List<Card> cards) {
        Card biggestCard = null;
        for (Card card : cards) {
            if (biggestCard == null) {
                biggestCard = card;
                continue;
            }
            if (card.compare(biggestCard)) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardSize() {
        return cardSize;
    }

    public void setCardSize(int cardSize) {
        this.cardSize = cardSize;
    }

    public int getRankTypeSize() {
        return rankTypeSize;
    }

    public void setRankTypeSize(int rankTypeSize) {
        this.rankTypeSize = rankTypeSize;
    }
}
