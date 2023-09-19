package ted.com.app.showdown;

public enum Suit {
    SPADE("黑桃"),
    HEART("愛心"),
    DIAMOND("菱形"),
    CLUB("梅花");

    private String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    public int showdown(Suit suit) {
        return 0;
    }
}
