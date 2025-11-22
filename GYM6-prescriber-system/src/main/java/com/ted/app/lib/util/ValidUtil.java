package com.ted.app.lib.util;

public class ValidUtil {


    public static boolean isValidTaiwanId(String id) {
        if (!id.matches("^[A-Z][12]\\d{8}$")) {
            return false;
        }

        // 將英文字母轉換為對應的數字
        String letters = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
        int letterIndex = letters.indexOf(id.charAt(0)) + 10;
        int firstDigit = letterIndex / 10;
        int secondDigit = letterIndex % 10;

        int[] digits = new int[11];
        digits[0] = firstDigit;
        digits[1] = secondDigit;
        for (int i = 2; i < 11; i++) {
            digits[i] = Character.getNumericValue(id.charAt(i - 1));
        }

        int sum = digits[0] + digits[1] * 9 + digits[2] * 8 + digits[3] * 7 +
                digits[4] * 6 + digits[5] * 5 + digits[6] * 4 + digits[7] * 3 +
                digits[8] * 2 + digits[9] + digits[10];

        return sum % 10 == 0;
    }


    public static boolean isInRange(int num1, int num2, int target){
        return num1 <= target && target <= num2;
    }

    public static boolean isInRange(float num1, float num2, float target){
        return num1 <= target && target <= num2;
    }
}
