package ted.com.app.showdown;

public abstract class Player {
    private String name;
    private int point;
    private Hand hand;

    public void nameHimsSelf() {

    }

    public void addHandCard(Card card) {

    }

    protected abstract Card show();

    /**
     * getter & setter
     **/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
