package ted.com.app.showdown;

import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer() {
    }

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void printHand() {
        System.out.printf("%s的手牌為:\n", super.getName());
        super.getHand().print();
    }

    @Override
    protected Card show(Scanner scanner) {
        System.out.println("請選擇要出的牌:");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Card card = super.getHand().show(index);
        super.getHand().remove(index);
        System.out.printf("%s出的牌為%s\n", super.getName(), card);
        return card;
    }
}
