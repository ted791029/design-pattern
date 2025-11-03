package ted.com.app.showdown;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Showdown game = new Showdown();
        Scanner scanner = new Scanner(System.in);
        game.start(scanner);
    }
}
