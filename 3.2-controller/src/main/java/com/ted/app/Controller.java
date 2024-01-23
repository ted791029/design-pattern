package com.ted.app;

import com.ted.app.command.Command;

public class Controller {
    private Command[] commands;
    public Controller(Command[] commands) {
        setCommands(commands);
    }

    public void clickButton(int num){
        if(num < 0 || num >= commands.length){
            System.out.printf("超出按鈕範圍\n");
            return;
        }
        Command command = commands[num];
        command.execute();
    }

    /**
     * getter & setter
     **/
    public Command[] getCommands() {
        return commands;
    }

    public void setCommands(Command[] commands) {
        this.commands = commands;
    }
}
