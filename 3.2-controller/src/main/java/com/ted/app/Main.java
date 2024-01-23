package com.ted.app;

import com.ted.app.command.*;

public class Main {
    public static void main(String[] args) {
        Command[] commands = new Command[6];
        commands[0] = new FanNextLevelCommand(new Fan());
        commands[1] = new FanPreLevelCommand(new Fan());
        commands[2] = new ACTurnOnCommand(new AC());
        commands[3] = new ACTurnOffCommand(new AC());
        commands[4] = new TVTurnOnCommand(new TV());
        commands[5] = new TVTurnOffCommand(new TV());

        Controller controller = new Controller(commands);
        for(int i = 0; i < 6; i++){
            controller.clickButton(i);
        }
    }
}