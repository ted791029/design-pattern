package ted.com.app.showdown;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {
    List<Card> cards;

    public DiscardPile() {
        setCards(new ArrayList<>());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void add(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> getTop(int num) {
        List<Card> cards = new ArrayList<>();
        int start = this.cards.size() - num;
        for (int i = start; i < this.cards.size(); i++) {
            cards.add(this.cards.get(i));
        }
        return cards;
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
