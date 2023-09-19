package ted.com.app.showdown;

import java.util.List;

public class Hand {
    private List<Card> cards;

    public int size(){
        return 0;
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
