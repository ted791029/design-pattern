package com.ted.app.command;

import com.ted.app.AC;

public class ACTurnOnCommand implements Command{
    private AC ac;

    public ACTurnOnCommand(AC ac) {
        setAc(ac);
    }

    @Override
    public void execute() {
        ac.turnOn();
    }

    public AC getAc() {
        return ac;
    }

    public void setAc(AC ac) {
        this.ac = ac;
    }
}
