package com.ted.app.command;

import com.ted.app.TV;

public class TVTurnOnCommand implements Command{
    private TV tv;

    public TVTurnOnCommand(TV tv) {
        setTv(tv);
    }

    @Override
    public void execute() {
        tv.turnOn();
    }

    public TV getTv() {
        return tv;
    }

    public void setTv(TV tv) {
        this.tv = tv;
    }
}
