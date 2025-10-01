package com.ted.app;

import com.ted.app.commands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class MainController {
    private Keyboard keyboard;
    private Stack<List<Command>> recordStack  = new Stack<>();
    private Stack<List<Command>> undoStack  = new Stack<>();
    private List<Command> totalCommands;

    public MainController(Keyboard keyboard, List<Command> commands) {
        setKeyboard(keyboard);
        setCommands(commands);
    }

    public void input(char button){

        if(!(('1' <= button && button <= '4') || ('a' <= button && button <= 'z'))){
            throw new IllegalArgumentException("button is invalid.");
        }

        if(button == '1'){
            setShortcutKey();
          return;
        }

        if(button == '2'){
            setMacro();
            return;
        }

        if(button == '3'){

            if(recordStack.size() == 0){
                System.out.println("無法Undo");
                return;
            }

            undo();
            return;
        }

        if(button == '4'){

            if(undoStack.size() == 0){
                System.out.println("無法Redo");
                return;
            }

            redo();
            return;
        }

        if(button >= 'a'|| button <= 'z'){
            pressKeyboard(button);
        }
    }

    public void addRecordStack(List<Command> commands){
        recordStack.add(commands);
    }

    public void cleanUndoStack(){
        while (undoStack.size() > 0){
            undoStack.pop();
        }
    }

    private char getChoseButton(){
        System.out.println("請a-z中選一個作為快捷鍵");
        Scanner scanner = new Scanner(System.in);
        char button = scanner.next().charAt(0);
        //ToDo 防呆 'a' <= button <= 'z'
        return button;
    }

    private List<Command> getChoseCommands(char button, boolean isMacro){
        System.out.println("要將哪一道指令設置到快捷鍵 " + button + " 上:");
        showCommands();
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        List<Command> commands = new ArrayList<>();
        String[] indexArr = inputStr.split(" ");
        //ToDo 防呆 輸入數字要在 0 - commands.size 之間
        for(String index : indexArr){
            commands.add(totalCommands.get(Integer.parseInt(index)));
        }
        return commands;
    }

    private void pressKeyboard(char button){
        keyboard.press(button);
    }

    private void redo(){
        List<Command> commands = undoStack.pop();
        for(Command command : commands){
            command.execute();
        }
        recordStack.add(commands);
    }

    private void setShortcutKey(){
        char button = getChoseButton();
        List<Command> commands = getChoseCommands(button, false);
        keyboard.setCommands(button, commands);
    }

    private void setMacro(){
        char button = getChoseButton();
        List<Command> commands = getChoseCommands(button, true);
        keyboard.setCommands(button, commands);
    }

    private void showCommands() {
        for(int i = 0; i < totalCommands.size(); i++){
            Command command = totalCommands.get(i);
            System.out.printf("(%d) %s\n", i, command.getName());
        }
    }

    private void undo(){
        List<Command> commands = recordStack.pop();
        for(Command command : commands){
            command.undo();
        }
        undoStack.add(commands);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public List<Command> getCommands() {
        return totalCommands;
    }

    public void setCommands(List<Command> commands) {
        this.totalCommands = commands;
    }
}
