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

    public void nameHimsSelf(String name) {
        setName(name);
    }

    public void addHandCard(Card card) {
        hand.add(card);
    }

    public void printHand(){
        System.out.printf("%s的手牌為:\n", name);
        hand.print();
    }

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
