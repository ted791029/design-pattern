package ted.com.app.showdown;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        System.out.print("請輸入有幾位玩家!\n");
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        System.out.println("你輸入的是: " +number);
    }
}
