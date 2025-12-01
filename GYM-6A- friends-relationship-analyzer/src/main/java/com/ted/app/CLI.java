package com.ted.app;

import com.ted.app.util.ScannerUtil;
import com.ted.app.util.ValidUtil;

public class CLI {

    private RelationshipAnalyzer analyzer;

    private RelationshipGraph graph;

    public CLI(RelationshipAnalyzer analyzer, String script) {
        this.analyzer = analyzer;
        updateScript(script);
    }

    public void start() {
        while (true) {
            String peopleStr = ScannerUtil.getInputStr("請輸入兩個人，並用空格隔開，EX:A,B");
            String[] people = peopleStr.split(",");

            if (people.length != 2) {
                System.out.println("輸入有誤");
                continue;
            }

            String name1 = people[0].toUpperCase();
            String name2 = people[1].toUpperCase();

            if (isInRange(name1, name2)) {
                System.out.println("請輸入A-Z");
                continue;
            }

            System.out.println("請選擇要做的事情");
            System.out.println("1.印出共同好友");
            System.out.println("2.印出兩人是否有關連");
            int input = ScannerUtil.getInputInteger(1, 2);

            switch (input) {
                case 1:
                    printMutualFriends(name1, name2);
                    break;
                case 2:
                    printIsConnection(name1, name2);
                    break;
            }
        }
    }

    private boolean isInRange(String name1, String name2) {
        return !ValidUtil.isInRange("A".charAt(0), "Z".charAt(0), name1.charAt(0)) || !ValidUtil.isInRange("A".charAt(0), "Z".charAt(0), name2.charAt(0));
    }

    private void printIsConnection(String name1, String name2) {
        System.out.printf("%s與%s是否有關連:%s\n", name1, name2, graph.hasConnection(name1, name2));
    }

    private void printMutualFriends(String name1, String name2) {
        System.out.printf("%s與%s的共同好友為%s\n", name1, name2, analyzer.getMutualFriends(name1, name2));
    }

    private void updateScript(String script) {
        graph = analyzer.parse(script);
    }
}
