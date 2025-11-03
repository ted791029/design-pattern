package com.ted.app;

import java.util.Optional;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Big2 big2 = new Big2(true, Optional.empty());
        Scanner scanner = new Scanner(System.in);
        big2.start(scanner);
    }
}
