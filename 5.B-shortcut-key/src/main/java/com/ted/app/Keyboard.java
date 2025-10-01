package com.ted.app;

import com.ted.app.commands.Command;

import java.util.List;

public class Keyboard {

    private MainController mainController;
    private List<Command>[] buttons = new List[26];

    public void clearAllCommands(){
        buttons = new List[26];
    }

    public void press(char button){
        //ToDo 防呆 'a' <= button <= 'z' && 按鍵有綁命令
        List<Command> commands = buttons[button - 'a'];
        for(Command command : commands){
            command.execute();
        }
        mainController.addRecordStack(commands);
        mainController.cleanUndoStack();
    }

    public void setCommands(char button, List<Command> commands){
        buttons[button - 'a'] = commands;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
