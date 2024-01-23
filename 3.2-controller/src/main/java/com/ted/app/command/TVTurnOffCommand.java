package com.ted.app.command;

import com.ted.app.TV;

public class TVTurnOffCommand implements Command{
    private TV tv;

    public TVTurnOffCommand(TV tv) {
        setTv(tv);
    }

    @Override
    public void execute() {
        tv.turnOff();
    }

    public TV getTv() {
        return tv;
    }

    public void setTv(TV tv) {
        this.tv = tv;
    }
}
