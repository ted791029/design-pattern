package ted.com.app.showdown;

public class Card {
    private Rank rank;
    private Suit suit;

    public Card() {

    }

    public Card(Suit suit, Rank rank) {
        setSuit(suit);
        setRank(rank);
    }

    public int showdown(Card card) {
        if (rank == card.rank) {
            return suit.showdown(card.suit);
        }
        return rank.showdown(card.rank);
    }

    public boolean isSame(Card card) {
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public String toString() {
        return suit.getSymbol() + rank.getSymbol();
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
