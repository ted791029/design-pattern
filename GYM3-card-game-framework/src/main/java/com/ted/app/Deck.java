package com.ted.app;

import com.ted.app.cards.Card;

import java.util.List;
import java.util.Stack;

import static com.ted.app.games.CardGame.random;

public class Deck {

    private Stack<Card> cards;

    private DiscardPile discardPile;

    public Deck(DiscardPile discardPile){
        setDiscardPile(discardPile);
        setCards(new Stack<>());
    }

    public Card drawCard() {

        if(isEmpty()){
            throw new RuntimeException("Deck is empty");
        }

        Stack<Card> cards = getCards();
        Card card = cards.pop();
        return card;
    }

    public boolean isEmpty(){
        Stack<Card> cards = getCards();
        return cards.isEmpty();
    }

    public void shuffle() {
        DiscardPile discardPile = getDiscardPile();
        List<Card> discardPileCards = discardPile.getCards();
        Stack<Card> deckCards = getCards();

        while (true){
            int size = discardPileCards.size();
            int index = random.nextInt(size);
            Card card = discardPileCards.get(index);
            deckCards.add(card);
            discardPileCards.remove(card);

            if(discardPileCards.isEmpty()){
                break;
            }
        }
    }

    //==========================================================//


    public Stack<Card> getCards() {
        return cards;
    }

    public void setCards(Stack<Card> cards) {
        this.cards = cards;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(DiscardPile discardPile) {
        this.discardPile = discardPile;
    }
}
