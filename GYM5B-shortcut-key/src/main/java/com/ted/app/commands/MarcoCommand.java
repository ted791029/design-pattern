package com.ted.app.commands;

import java.util.List;

public class MarcoCommand implements Command {
    
    private List<Command> commands;

    public MarcoCommand(List<Command> commands){
        setCommands(commands);
    }

    @Override
    public void execute() {
        for(Command command : commands){
            command.execute();
        }
    }

    @Override
    public void undo() {
        for(Command command : commands){
            command.undo();
        }
    }

    @Override
    public String getName() {
        return null;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
