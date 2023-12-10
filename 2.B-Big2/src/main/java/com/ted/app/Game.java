package com.ted.app;

public class Game {

    public static void main(String[] args) {
        String name = "John";
        int age = 30;
        double salary = 50000.50;

        // 左对齐
        System.out.printf("Name: %-10s Age: %-5d Salary: %-10.2f%n", name, age, salary);

        // 右对齐（默认）
        System.out.printf("Name: %10s Age: %5d Salary: %10.2f%n", name, age, salary);

        // 居中对齐
        System.out.printf("Name: %^10s Age: %^5d Salary: %^10.2f%n", name, age, salary);
    }
}
