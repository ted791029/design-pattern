package ted.com.app.showdown;

import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        init();
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int targetIndex = (int) (Math.random() * (cards.size() - i));
            swap(i, targetIndex);
        }
    }

    public Card drawCard() {
        return cards.pop();
    }

    public int size(){
        return cards.size();
    }

    private void init() {
        Stack<Card> stack = new Stack<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                stack.add(new Card(suit, rank));
            }
        }
        setCards(stack);
    }

    private void swap(int index1, int index2) {
        Card card = cards.get(index1);
        cards.set(index1, cards.get(index2));
        cards.set(index2, card);
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
