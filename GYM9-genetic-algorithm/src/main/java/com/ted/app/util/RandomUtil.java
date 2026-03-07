package com.ted.app.util;

import java.util.Random;

public class RandomUtil {

    private static final Random random = new Random();

    public static int nextInt(int size){
        return random.nextInt(size);
    }

    public static double nextDouble(double size) {
        return random.nextDouble() * size;
    }
}
