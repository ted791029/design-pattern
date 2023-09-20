package ted.com.app.showdown;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {
    List<Card> cards;

    public DiscardPile() {
        setCards(new ArrayList<>());
    }


    public int size() {
        return cards.size();
    }

    public void add(Card card){
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
