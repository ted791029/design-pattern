package com.ted.app;

import com.ted.app.commands.Command;

public class Keyboard {

    private MainController mainController;
    private Command[] buttons = new Command[26];

    public void clearAllCommands(){
        buttons = new Command[26];
    }

    public void press(char button){
        //ToDo 防呆 'a' <= button <= 'z' && 按鍵有綁命令
        Command command = buttons[button - 'a'];
        command.execute();
        mainController.addRecordStack(command);
        mainController.cleanUndoStack();
    }

    public void setCommands(char button, Command command){
        buttons[button - 'a'] = command;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
