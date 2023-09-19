package ted.com.app.showdown;

import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        this.init();
    }

    public void shuffle() {

    }

    public Card drawCard() {
        return null;
    }

    private void init() {
        Stack<Card> stack = new Stack<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                stack.add(new Card(suit, rank));
            }
        }
        this.setCards(stack);
    }

    /**
     * getter & setter
     **/
    public Stack<Card> getCards() {
        return cards;
    }

    public void setCards(Stack<Card> cards) {
        this.cards = cards;
    }
}
