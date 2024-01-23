package com.ted.app;

public class Fan {
    int level = 1;

    public void next(){
        level++;
        System.out.printf("風扇風力増強\n");
    }
    public void pre(){
        level--;
        System.out.printf("風扇風力減弱\n");
    }
}
