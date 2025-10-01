package com.ted.app;

import com.ted.app.commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Telecom telecom = new Telecom();
        Tank tank = new Tank();
        Keyboard keyboard = new Keyboard();
        List<Command> totalCommand = new ArrayList<>();
        totalCommand.add(new MoveTankForwardCommand(tank));
        totalCommand.add(new MoveTankBackwardCommand(tank));
        totalCommand.add(new ConnectTelecomCommand(telecom));
        totalCommand.add(new DisconnectTelecomCommand(telecom));
        totalCommand.add(new ResetMainControllerKeyboardCommand(keyboard));
        MainController controller = new MainController(keyboard, totalCommand);
        keyboard.setMainController(controller);

        while (true){
            System.out.println("請選擇:");
            System.out.println("(1) 快捷鍵設置");
            System.out.println("(2) 巨集設置");
            System.out.println("(3) undo");
            System.out.println("(4) redo");
            System.out.println("(字母) 按下Keyboard 鍵盤");
            Scanner scanner = new Scanner(System.in);
            String inputStr = scanner.next();
            char button = inputStr.charAt(0);
            controller.input(button);
        }
    }
}
