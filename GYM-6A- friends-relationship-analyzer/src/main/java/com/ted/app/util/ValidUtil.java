package com.ted.app.util;

public class ValidUtil {

    public static boolean isInRange(int min, int max, int number){
        if (number >=  min && number <= max) {
           return true;
        }
        return false;
    }
}
