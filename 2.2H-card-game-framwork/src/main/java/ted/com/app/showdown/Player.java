package ted.com.app.showdown;

import java.util.Scanner;

public abstract class Player {
    private String name;
    private int point;
    private Hand hand;

    public Player() {
        setHand(new Hand());
        setPoint(0);
    }

    public Player(String name) {
        setName(name);
        setHand(new Hand());
        setPoint(0);
    }

    public void addHandCard(Card card) {
        hand.add(card);
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public boolean handIsEmpty() {
        return hand.isEmpty();
    }

    public Player findWinner(Player player) {
        return point > player.point ? this : player;
    }

    public void nameHimsSelf(String name) {
        setName(name);
    }

    protected abstract void printHand();

    protected abstract Card show(Scanner scanner);

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
