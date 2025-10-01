package com.ted.app.commands;

public interface Command {
    public void execute();

    public void undo();

    public String getName();
}
