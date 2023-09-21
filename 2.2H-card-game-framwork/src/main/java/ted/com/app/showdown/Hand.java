package ted.com.app.showdown;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        setCards(new ArrayList<>());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return cards.size();
    }

    public Card show(int index) {
        return cards.get(index);
    }

    public void print() {
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            System.out.printf("%d.%s ", i + 1, card);
        }
        System.out.println();
    }

    public void remove(int index) {
        cards.remove(index);
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
