package com.ted.app.command;

import com.ted.app.AC;

public class ACTurnOffCommand implements Command{
    private AC ac;

    public ACTurnOffCommand(AC ac) {
        setAc(ac);
    }

    @Override
    public void execute() {
        ac.turnOff();
    }

    public AC getAc() {
        return ac;
    }

    public void setAc(AC ac) {
        this.ac = ac;
    }
}
