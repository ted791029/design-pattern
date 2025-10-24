package com.ted.app;

import com.ted.app.commands.Command;
import com.ted.app.commands.MarcoCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class MainController {
    private Keyboard keyboard;
    private Stack<Command> recordStack  = new Stack<>();
    private Stack<Command> undoStack  = new Stack<>();
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

    public void addRecordStack(Command command){
        recordStack.add(command);
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
        if(!(('a' <= button && button <= 'z'))){
            throw new IllegalArgumentException("button is invalid.");
        }
        return button;
    }

    private Command getChoseCommand(char button){
        String getInputStr = getChoseInputStr(button);
        if (!getInputStr.matches("-?\\d+")) {
            throw new IllegalArgumentException("button is invalid.");
        }
        int index = Integer.parseInt(getInputStr);
        if(index < 0 || index >= getTotalCommands().size()){
            throw new IllegalArgumentException("button is invalid.");
        }

        Command command = totalCommands.get(index);
        return command;
    }

    private List<Command> getChoseCommands(char button){
        List<Command> commands = new ArrayList<>();
        //用空格區隔
        String[] indexArr = getChoseInputStr(button).split(" ");
        for(String indexStr : indexArr){
            if (!indexStr.matches("-?\\d+")) {
                throw new IllegalArgumentException("button is invalid.");
            }
            int index = Integer.parseInt(indexStr);
            if(index < 0 || index >= getTotalCommands().size()){
                throw new IllegalArgumentException("button is invalid.");
            }
            commands.add(totalCommands.get(index));
        }
        return commands;
    }

    private String getChoseInputStr(char button){
        System.out.println("要將哪一道指令設置到快捷鍵 " + button + " 上:");
        showCommands();
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        return inputStr;
    }

    private void pressKeyboard(char button){
        keyboard.press(button);
    }

    private void redo(){
        Command command = undoStack.pop();
        command.execute();
        recordStack.add(command);
    }

    private void setShortcutKey(){
        char button = getChoseButton();
        Command command = getChoseCommand(button);
        keyboard.setCommands(button, command);
    }

    private void setMacro(){
        char button = getChoseButton();
        List<Command> commands = getChoseCommands(button);
        MarcoCommand marcoCommands = new MarcoCommand(commands);
        keyboard.setCommands(button, marcoCommands);
    }

    private void showCommands() {
        for(int i = 0; i < totalCommands.size(); i++){
            Command command = totalCommands.get(i);
            System.out.printf("(%d) %s\n", i, command.getName());
        }
    }

    private void undo(){
        Command command = recordStack.pop();
        command.undo();
        undoStack.add(command);
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

    public Stack<Command> getRecordStack() {
        return recordStack;
    }

    public void setRecordStack(Stack<Command> recordStack) {
        this.recordStack = recordStack;
    }

    public Stack<Command> getUndoStack() {
        return undoStack;
    }

    public void setUndoStack(Stack<Command> undoStack) {
        this.undoStack = undoStack;
    }

    public List<Command> getTotalCommands() {
        return totalCommands;
    }

    public void setTotalCommands(List<Command> totalCommands) {
        this.totalCommands = totalCommands;
    }
}
