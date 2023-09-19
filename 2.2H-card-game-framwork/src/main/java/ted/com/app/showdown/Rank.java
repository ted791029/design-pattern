package ted.com.app.showdown;

public enum Rank {
    ACE("A", 12),
    TWO("2", 0),
    THREE("3", 1),
    FOUR("4", 2),
    FIVE("5", 3),
    SIX("6", 4),
    SEVEN("7", 5),
    EIGHT("8", 6),
    NINE("9", 7),
    TEN("10", 8),
    JACK("J", 9),
    QUEEN("Q", 10),
    KING("K", 11);

    private String symbol;
    private int score;

    Rank(String symbol, int score) {
        this.score = score;
        this.symbol = symbol;
    }

    public int showdown(Rank rank) {
        return 0;
    }
}
