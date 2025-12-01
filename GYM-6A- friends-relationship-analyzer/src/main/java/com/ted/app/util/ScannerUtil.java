package com.ted.app.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerUtil {

    public static final Scanner scanner = new Scanner(System.in);

    public static int getInputInteger(int min, int max){
        while (true) {
            String inputStr = getInputStr();

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

    public static List<Integer> getInputIntegers(int min, int max, String spilt){
        List<Integer> result = new ArrayList<>();
        while (true) {
            String inputStr = getInputStr();
            String[] inputStrArr = inputStr.split(spilt);

            for(String current : inputStrArr){
                if (!current.trim().matches("-?\\d+")) { // 可處理負數
                    System.out.println("輸入錯誤，請輸入數字");
                    continue;
                }

                int number = Integer.parseInt(current);

                if(!ValidUtil.isInRange(min, max, number)){
                    System.out.println("輸入錯誤，數字範圍為:" + min + " ~ " + max);
                    continue;
                }
                result.add(number);
            }
            return result;
        }
    }

    public static List<Integer> getInputIntegers(int min, int max, String spilt, int targetCount){
        List<Integer> result = new ArrayList<>();
        while (true) {
            String inputStr = getInputStr();
            String[] inputStrArr = inputStr.split(spilt);

            if(inputStrArr.length > targetCount){
                System.out.println("選擇超過數量限制");
                continue;
            }

            for(String current : inputStrArr){
                current = current.trim();
                if (!current.matches("-?\\d+")) { // 可處理負數
                    System.out.println("輸入錯誤，請輸入數字");
                    continue;
                }

                int number = Integer.parseInt(current);

                if(!ValidUtil.isInRange(min, max, number)){
                    System.out.println("輸入錯誤，數字範圍為:" + min + " ~ " + max);
                    continue;
                }
                result.add(number);
            }
            return result;
        }
    }

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

    public static String getInputStr(){
        return scanner.nextLine();
    }

    public static String getInputStr(String msg){
        System.out.println(msg);
        return scanner.nextLine();
    }

}
