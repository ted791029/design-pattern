package com.ted.app.commands;

import com.ted.app.Keyboard;

public class ResetMainControllerKeyboardCommand implements Command{
    private Keyboard keyboard;

    public ResetMainControllerKeyboardCommand(Keyboard keyboard) {
        setKeyboard(keyboard);
    }

    @Override
    public void execute() {
        keyboard.clearAllCommands();
    }

    @Override
    public void undo() {

    }

    @Override
    public String getName() {
        return "ResetMainControllerKeyboard";
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }
}
