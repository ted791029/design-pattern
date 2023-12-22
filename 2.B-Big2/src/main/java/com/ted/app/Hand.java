package com.ted.app;

import com.ted.app.Card.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hand {
    List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void add(Card card) {
        int index = 0;
        for (Card temp : cards) {
            if (temp.compare(card)) break;
            index++;
        }
        cards.add(index, card);
    }

    public Card fristCard() {
        return cards.get(0);
    }

    public boolean hasTheCard(Card target) {
        for (Card card : cards) {
            if (card.equal(target)) {
                return true;
            }
        }
        return false;
    }

    public List<Card> play(List<Integer> indexes) {
        List<Card> cards = getCards(indexes);
        sort(cards);
        return cards;
    }

    public void show() {
        for (int i = 0; i < cards.size(); i++) {
            System.out.printf("%-6d", i);
        }
        System.out.printf("\n");
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            String str = card.getSuitSymbol() + "[" + card.getRankSymbol() + "]";
            System.out.printf("%-6s", str);
        }
        System.out.printf("\n");
    }

    public int size() {
        return cards.size();
    }

    public void remove(List<Card> playCards) {
        for (Card card : playCards) {
            cards.remove(card);
        }
    }

    private List<Card> getCards(List<Integer> indexes) {
        List<Card> cards = new ArrayList<>();
        for (int index : indexes) {
            if (index < 0 || index >= this.cards.size()) {
                throw new RuntimeException("超過輸入範圍");
            }
            cards.add(this.cards.get(index));
        }
        return cards;
    }

    private void sort(List<Card> cards) {
        cards.sort(
                new Comparator<Card>() {
                    @Override
                    public int compare(Card o1, Card o2) {
                        if (o1.compare(o2)) return 1;
                        return 0;
                    }
                }
        );
    }

    /**
     * getter & setter
     **/
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
