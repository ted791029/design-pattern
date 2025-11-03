package ted.com.app.showdown;

import java.util.Scanner;

public class AIPlayer extends Player {
    public AIPlayer() {
    }

    public AIPlayer(String name) {
        super(name);
    }

    public AIPlayer(int num) {
        String name = "機器人" + num;
        setName(name);
    }


    @Override
    public void printHand() {

    }

    @Override
    protected Card show(Scanner scanner) {
        int size = super.getHand().size();
        int index = (int) (Math.random() * size);
        Card card = super.getHand().show(index);
        super.getHand().remove(index);
        System.out.printf("%s出的牌為%s\n", super.getName(), card);
        return card;
    }
}
