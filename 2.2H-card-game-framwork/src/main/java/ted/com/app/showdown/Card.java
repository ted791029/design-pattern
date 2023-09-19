package ted.com.app.showdown;

public class Card {
    private Rank rank;
    private Suit suit;

    public Card(){

    }

    public Card(Suit suit, Rank rank){
        this.setSuit(suit);
        this.setRank(rank);
    }

    public int showdown(Card card) {
        return 0;
    }

    public boolean isSame(Card card){
        return this.suit == card.suit && this.rank == card.rank;
    }

    /**
     * getter & setter
     **/
    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }
}
