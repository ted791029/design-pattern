package com.ted.app.util;
import java.util.Scanner;

public class ScannerUtil {

    public static final Scanner scanner = new Scanner(System.in);

    public static int getInputInteger(String msg, int min, int max){
        while (true) {
            String inputStr = getInputStr(msg);

            if (!inputStr.matches("-?\\d+")) { // 可處理負數
                System.out.println("輸入錯誤，請輸入數字");
                continue;
            }

            int number = Integer.parseInt(inputStr);

            if(!ValidUtil.isInRange(min, max, number)){
                System.out.println("輸入錯誤，數字範圍為:" + min + " ~ " + max);
                continue;
            }
            return number;
        }
    }

    public static String getInputStr(String msg){
        System.out.println(msg);
        return scanner.nextLine();
    }

}
